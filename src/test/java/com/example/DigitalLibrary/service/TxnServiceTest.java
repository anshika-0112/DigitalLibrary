package com.example.DigitalLibrary.service;

import com.example.DigitalLibrary.exception.TxnException;
import com.example.DigitalLibrary.model.Student;
import com.example.DigitalLibrary.model.Txn;
import com.example.DigitalLibrary.repository.TxnRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TxnServiceTest {

    @InjectMocks
    private TxnService txnService;

    @Mock
    private TxnRepository txnRepository;

    @Mock
    private StudentService studentService;

    @Mock
    private BookService bookService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(txnService,"validUpto","14");
        ReflectionTestUtils.setField(txnService,"finePerDay","1");
    }

    @Test
    public void testCalculateSettlementAmount() throws ParseException {
        Date date=new SimpleDateFormat("YYYY-MM-DD").parse("2023-01-15");
        Txn txn= Txn.builder().createdOn(date).paidAmount(200).build();
        int amount=txnService.calculateSettlementAmount(txn);
        Assert.assertEquals(180,amount);
    }

    @Test(expected = TxnException.class)
    public void testFilterStudent() throws TxnException {
        when(studentService.filter(any(),any(),any())).thenReturn(null);
        txnService.filterStudent(any(),any(),any());
    }

    @Test(expected = TxnException.class)
    public void testFilterStudentEmptyList() throws TxnException {
        when(studentService.filter(any(),any(),any())).thenReturn(new ArrayList<>());
        txnService.filterStudent(any(),any(),any());
    }

    @Test
    public void testFilterStudentListWithData() throws TxnException {
        List<Student> list=new ArrayList<>();
        Student student1=Student.builder().id(1).build();
        list.add(student1);
        Student student2=Student.builder().id(1).build();
        list.add(student2);
        when(studentService.filter(any(),any(),any())).thenReturn(list);
        Student student=txnService.filterStudent(any(),any(),any());
        Assert.assertEquals(student1.getId(),student.getId());
    }
}
