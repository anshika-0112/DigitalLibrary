package com.example.DigitalLibrary.service;

import com.example.DigitalLibrary.exception.TxnException;
import com.example.DigitalLibrary.model.*;
import com.example.DigitalLibrary.repository.TxnRepository;
import com.example.DigitalLibrary.request.TxnCreateRequest;
import com.example.DigitalLibrary.request.TxnReturnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {

    @Autowired
    private BookService bookService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TxnRepository txnRepository;

    @Value("${student.valid.days}")
    private String validUpto;

    @Value("${student.delayed.finePeriod}")
    private String finePerDay;


    private Student filterStudent(StudentFilterType studentFilterType,Operator operator, String value) throws TxnException {
        List<Student> studentList=studentService.filter(StudentFilterType.CONTACT, Operator.EQUALS,value);
        if(studentList==null || studentList.isEmpty()){
            throw new TxnException("Student is not registered to the Library");
        }
        return studentList.get(0);
    }

    private Book filterBook(FilterType filterType,Operator operator,String value) throws TxnException {
        List<Book> bookList=bookService.filter(FilterType.BOOK_NO, Operator.EQUALS,value);
        if(bookList==null || bookList.isEmpty()) {
            throw new TxnException("Book is not available in Library");
        }
        return bookList.get(0);
    }

    @Transactional(rollbackFor = {TxnException.class})
    public String createTransaction(TxnCreateRequest txnCreateRequest) throws TxnException {

        Student student=filterStudent(StudentFilterType.CONTACT,Operator.EQUALS, txnCreateRequest.getStudentContact());
        Book book=filterBook(FilterType.BOOK_NO,Operator.EQUALS, txnCreateRequest.getBookNo());
        if(book.getStudent()!=null){
            throw new TxnException("Book is assigned to someone else");
        }
        String txnId= UUID.randomUUID().toString();

        Txn txn= Txn.builder().student(student).
                book(book).
                txnId(txnId).
                paidAmount(txnCreateRequest.getPaidAmount()).
                status(TxnStatus.ISSUED).build();
        txn = txnRepository.save(txn);
        book.setStudent(student);
        bookService.saveUpdate(book);
        return txn.getTxnId();
    }

    @Transactional(rollbackFor = {TxnException.class})
    public int returnBook(TxnReturnRequest txnReturnRequest) throws Exception {
        Student student=filterStudent(StudentFilterType.CONTACT,Operator.EQUALS, txnReturnRequest.getStudentContact());
        Book book=filterBook(FilterType.BOOK_NO,Operator.EQUALS, txnReturnRequest.getBookNo());

        if(book.getStudent()!=null && book.getStudent().equals(student)){
            Txn txn=txnRepository.findByTxnId(txnReturnRequest.getTxnId());
            if(txn==null){
                throw new Exception("No transaction has been found with this transaction id");
            }
            int amount = calculateSettlementAmount(txn);
            if(amount== txn.getPaidAmount()){
                txn.setStatus(TxnStatus.RETURNED);
            }
            else {
                txn.setStatus(TxnStatus.FINED);
            }
            txn.setPaidAmount(amount);

            book.setStudent(null);
            bookService.saveUpdate(book);
            return amount;
        }else{
            throw new TxnException("Book is not assigned to you");
        }
    }

    private int calculateSettlementAmount(Txn txn) {
        long issueTime = txn.getCreatedOn().getTime();
        long returnTime= System.currentTimeMillis();
        long timeDiff=returnTime-issueTime;
        int daysPassed=(int) TimeUnit.DAYS.convert(timeDiff,TimeUnit.MILLISECONDS);
        if(daysPassed>Integer.parseInt(validUpto)){
            int fineAmount=((daysPassed-Integer.parseInt(validUpto))*Integer.parseInt(finePerDay));
            return txn.getPaidAmount()-fineAmount;
        }
        return txn.getPaidAmount();
    }
}
