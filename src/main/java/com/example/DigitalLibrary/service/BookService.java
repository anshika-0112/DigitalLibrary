package com.example.DigitalLibrary.service;

import com.example.DigitalLibrary.model.*;
import com.example.DigitalLibrary.repository.AuthorRepository;
import com.example.DigitalLibrary.repository.BookRepository;
import com.example.DigitalLibrary.repository.RedisDataRepository;
import com.example.DigitalLibrary.request.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private RedisDataRepository redisDataRepository;

    public List<Book> filter(FilterType filterBy, Operator operator, String value) {
        switch (operator){
            case EQUALS:
                switch (filterBy){
                    case BOOK_NO :
                        List<Book> list=redisDataRepository.getBookByBookNo(value);
                        if(list!=null && !list.isEmpty()){
                            return list;
                        }
                        list=bookRepository.findByBookNo(value);
                        if(!list.isEmpty()){
                            redisDataRepository.setBookToRedisByBookNo(list.get(0));
                        }
                        return list;
                    case AUTHOR_NAME: return bookRepository.findByAuthorName(value);
                    case COST:return bookRepository.findByCost(Integer.parseInt(value));
                    case BOOK_TYPE: return bookRepository.findByType(BookType.valueOf(value));
                }
            case LESS_THAN:
                switch (filterBy){
                    case COST : return bookRepository.findByCostLessThan(Integer.parseInt(value));
                }
            default:
                return new ArrayList<>();
        }
    }

    public Book createBook(BookCreateRequest bookCreateRequest){
        Author author=authorRepository.findByEmail(bookCreateRequest.getAuthorEmail());
        if(author==null){
            author = authorRepository.save(bookCreateRequest.toAuthor());
        }
        Book book= bookCreateRequest.toBook();
        book.setAuthor(author);
        book=bookRepository.save(book);
        redisDataRepository.setBookToRedis(book);
        return book;
    }

    public void saveUpdate(Book book){
        bookRepository.save(book);
    }

}
