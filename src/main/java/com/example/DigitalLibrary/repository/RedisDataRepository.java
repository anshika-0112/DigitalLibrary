package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RedisDataRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String BOOK_KEY="book:";
    public void setBookToRedis(Book book){
        setBookToRedisByAuthorName(book);
        setBookToRedisByBookNo(book);
        setBookToRedisByBookType(book);
    }

    private void setBookToRedisByBookType(Book book) {
        redisTemplate.opsForList().leftPush(BOOK_KEY+book.getType(),book);
    }

    public void setBookToRedisByBookNo(Book book) {
        redisTemplate.opsForList().leftPush(BOOK_KEY+book.getBookNo(),book);
    }

    private void setBookToRedisByAuthorName(Book book) {
        redisTemplate.opsForList().leftPush(BOOK_KEY+book.getAuthor().getName(),book);
    }

    public List<Book> getBookByBookNo(String value) {
        return (List<Book>) (Object) redisTemplate.opsForList().range(BOOK_KEY+value,0,-1);
    }
}
