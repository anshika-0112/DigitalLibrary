package com.example.DigitalLibrary.service;

import com.example.DigitalLibrary.model.Operator;
import com.example.DigitalLibrary.model.Student;
import com.example.DigitalLibrary.model.StudentFilterType;
import com.example.DigitalLibrary.repository.StudentRepository;
import com.example.DigitalLibrary.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    public Student createStudent(StudentCreateRequest studentCreateRequest) {
        List<Student> studentList=studentRepository.findByPhoneNo(studentCreateRequest.getPhoneNo());
        Student student=null;
        if(studentList.isEmpty()){
           student=studentRepository.save(studentCreateRequest.toStudent());
           return student;
        }
        student=studentList.get(0);
        return student;
    }

    public List<Student> filter(StudentFilterType studentFilterType, Operator operator,String value) {
        switch (operator){
            case EQUALS :
                switch (studentFilterType) {
                    case CONTACT:
                        return studentRepository.findByPhoneNo(value);
                }
            default : return new ArrayList<>();
        }
    }
}
