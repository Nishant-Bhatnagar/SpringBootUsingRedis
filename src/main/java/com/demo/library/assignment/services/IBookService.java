package com.demo.library.assignment.services;

import com.demo.library.assignment.model.Book;

import java.util.List;


public interface IBookService {
    public Book save(Book book);

    public List<Book> getAllBooks();

    public Book findBookById(int bookId);

    public String deleteBookById(int id);
}
