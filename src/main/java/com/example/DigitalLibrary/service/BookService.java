package com.example.DigitalLibrary.service;

import com.example.DigitalLibrary.model.Author;
import com.example.DigitalLibrary.model.Book;
import com.example.DigitalLibrary.model.BookType;
import com.example.DigitalLibrary.repository.AuthorRepository;
import com.example.DigitalLibrary.repository.BookRepository;
import com.example.DigitalLibrary.request.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    public Book createBook(BookCreateRequest bookCreateRequest){
        Author author=authorRepository.findByEmail(bookCreateRequest.getAuthorEmail());
        if(author==null){
            author = authorRepository.save(bookCreateRequest.toAuthor());
        }
        Book book= bookCreateRequest.toBook();
        book.setAuthor(author);
        return bookRepository.save(book);
    }

}
