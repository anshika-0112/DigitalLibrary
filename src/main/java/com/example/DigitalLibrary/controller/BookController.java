package com.example.DigitalLibrary.controller;

import com.example.DigitalLibrary.model.Book;
import com.example.DigitalLibrary.model.FilterType;
import com.example.DigitalLibrary.model.Operator;
import com.example.DigitalLibrary.request.BookCreateRequest;
import com.example.DigitalLibrary.response.GenericResponse;
import com.example.DigitalLibrary.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    private Book createBook(@RequestBody @Valid BookCreateRequest bookCreateRequest){
        return bookService.createBook(bookCreateRequest);
    }

    @GetMapping("/filter")
    private ResponseEntity<GenericResponse<List<Book>>> filter(@RequestParam("filterBy") FilterType filterBy, @RequestParam("operator") Operator operator,
                                                              @RequestParam("value") String value){
        List<Book> list= bookService.filter(filterBy,operator,value);
        GenericResponse<List<Book>> response= new GenericResponse<>(list,"","Success","200");
        ResponseEntity entity=new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }
}
