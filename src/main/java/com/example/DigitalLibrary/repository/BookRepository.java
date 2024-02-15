package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
