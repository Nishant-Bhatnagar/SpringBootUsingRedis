package com.demo.library.assignment.controller;

import com.demo.library.assignment.model.Book;
import com.demo.library.assignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("AddNewBook")
    public Book saveBooks(@RequestBody Book book)
    {
        return bookRepository.save(book);
    }

    @GetMapping("GetAllBooks")
    public List<Book> getAllBooks()
    {
        return bookRepository.getAllBooks();
    }
    @GetMapping("/{id}")
    public Object getBookById(@PathVariable int id)
    {
        return bookRepository.findBookById(id);
    }
    @DeleteMapping("/{id}")
    public String removeBook(@PathVariable int id){
        return bookRepository.deleteBookById(id);
    }


}
