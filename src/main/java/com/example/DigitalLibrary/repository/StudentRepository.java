package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByPhoneNo(String phoneNo);

    List<Student> findByName(String name);

    List<Student> findByAddress(String address);
}
