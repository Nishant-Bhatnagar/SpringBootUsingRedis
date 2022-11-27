package com.demo.library.assignment.services.impl;

import com.demo.library.assignment.model.Book;
import com.demo.library.assignment.repository.BookRepositoryInterface;
import com.demo.library.assignment.services.BookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookServiceInterface {

    @Autowired
    private BookRepositoryInterface bookRepositoryInterface;
    @Override
    public Book save(Book book) {
        return bookRepositoryInterface.save(book);
    }

    @Override
    public List<Book> getAllBooks() {

        Iterable<Book> all = bookRepositoryInterface.findAll();
        List<Book> bookList = new ArrayList<>();
        all.forEach(bookList::add);
        return bookList;

    }

    @Override
    public Book findBookById(int bookId) {
        return bookRepositoryInterface.findById(bookId).orElse(null);

    }

    @Override
    public String deleteBookById(int id) {
        bookRepositoryInterface.deleteById(id);
        return "Deleted";
    }
}
