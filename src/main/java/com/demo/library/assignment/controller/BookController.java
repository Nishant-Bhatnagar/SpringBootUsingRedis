package com.demo.library.assignment.controller;

import com.demo.library.assignment.model.Book;
import com.demo.library.assignment.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book/")
public class BookController {
    @Autowired
    private IBookService bookService;


    @PostMapping("AddNewBook")
    public String saveBooks(@RequestBody Book book)
    {
         bookService.save(book);
        return "Book added";
    }

    @GetMapping("GetAllBooks")
    public List<Book> getAllBooks()
    {
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id)
    {

        return bookService.findBookById(id);
    }
    @DeleteMapping("/{id}")
    public String removeBook(@PathVariable int id){
        return bookService.deleteBookById(id);
    }


}
