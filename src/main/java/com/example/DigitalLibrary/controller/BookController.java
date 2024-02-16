package com.example.DigitalLibrary.controller;

import com.example.DigitalLibrary.model.Book;
import com.example.DigitalLibrary.model.FilterType;
import com.example.DigitalLibrary.model.Operator;
import com.example.DigitalLibrary.request.BookCreateRequest;
import com.example.DigitalLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    private Book createBook(@RequestBody BookCreateRequest bookCreateRequest){
        return bookService.createBook(bookCreateRequest);
    }

    @GetMapping("/filter")
    private List<Book> filter(@RequestParam("filterBy") FilterType filterBy, @RequestParam("operator") Operator operator,
                              @RequestParam("value") String value){
        return bookService.filter(filterBy,operator,value);
    }
}
