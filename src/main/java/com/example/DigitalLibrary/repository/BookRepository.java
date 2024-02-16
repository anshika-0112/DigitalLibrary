package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.Book;
import com.example.DigitalLibrary.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByBookNo(String bookNo);

    List<Book> findByAuthorName(String authorName);

    List<Book> findByCost(int cost);

    List<Book> findByType(BookType bookType);

    List<Book> findByCostLessThan(int cost);
}
