package com.example.DigitalLibrary.controller;

import com.example.DigitalLibrary.model.Book;
import com.example.DigitalLibrary.model.Student;
import com.example.DigitalLibrary.request.BookCreateRequest;
import com.example.DigitalLibrary.request.StudentCreateRequest;
import com.example.DigitalLibrary.service.BookService;
import com.example.DigitalLibrary.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    private Student createStudent(@RequestBody StudentCreateRequest studentCreateRequest){
        return studentService.createStudent(studentCreateRequest);
    }
}
