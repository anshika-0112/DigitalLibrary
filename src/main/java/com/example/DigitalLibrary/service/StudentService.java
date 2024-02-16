package com.example.DigitalLibrary.service;

import com.example.DigitalLibrary.model.Student;
import com.example.DigitalLibrary.repository.StudentRepository;
import com.example.DigitalLibrary.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    public Student createStudent(StudentCreateRequest studentCreateRequest) {
        Student student=studentRepository.findByPhoneNo(studentCreateRequest.getPhoneNo());
        if(student==null){
           student=studentRepository.save(studentCreateRequest.toStudent());
        }
        return student;
    }
}
