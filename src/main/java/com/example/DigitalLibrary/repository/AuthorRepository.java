package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author findByEmail(String email);
}
