package com.demo.library.assignment.controller;

import com.demo.library.assignment.model.Book;
import com.demo.library.assignment.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;


    @PostMapping("AddNewBook")
    public String saveBooks(@RequestBody Book book)
    {
         bookRepository.save(book);
        return "Book added";
    }

    @GetMapping("GetAllBooks")
    public List<Book> getAllBooks()
    {
        return bookRepository.getAllBooks();
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id)
    {

        return bookRepository.findBookById(id);
    }
    @DeleteMapping("/{id}")
    public String removeBook(@PathVariable int id){
        return bookRepository.deleteBookById(id);
    }


}
