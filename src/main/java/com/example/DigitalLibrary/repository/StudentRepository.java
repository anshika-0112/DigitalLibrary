package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByPhoneNo(String phoneNo);
}
