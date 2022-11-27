package com.demo.library.assignment.repository;

import com.demo.library.assignment.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    public static final String HASH_KEY = "book";
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    public Book save(Book book){
        template.opsForHash().put(HASH_KEY,book.getId(), book);
        return book;
    }

    public List<Book> getAllBooks(){
        return template.opsForHash().values(HASH_KEY);
    }
    public Object findBookById(int id){
        return template.opsForHash().get(HASH_KEY, id);
    }
    public String deleteBookById(int id){
        template.opsForHash().delete(HASH_KEY, id);
        return "Book removed successfully";
    }
}
