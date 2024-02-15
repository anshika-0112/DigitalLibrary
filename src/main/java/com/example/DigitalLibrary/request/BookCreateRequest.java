package com.example.DigitalLibrary.request;

import com.example.DigitalLibrary.model.Author;
import com.example.DigitalLibrary.model.Book;
import com.example.DigitalLibrary.model.BookType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequest {
    private String name;
    private String bookNo;
    private int cost;
    private BookType type;
    private String authorName;
    private String authorEmail;

    public Author toAuthor(){
        return Author.builder().name(this.authorName).email(this.authorEmail).build();
    }

    public Book toBook(){
        return Book.builder().
                name(this.name).
                bookNo(this.bookNo).
                cost(this.cost).
                type(this.type).
                build();
    }
}
