package com.demo.library.assignment.services.impl;

import com.demo.library.assignment.model.Book;
import com.demo.library.assignment.repository.IBookRepository;
import com.demo.library.assignment.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IBookServiceImpl implements IBookService {

    @Autowired
    private IBookRepository IBookRepository;
    @Override
    public Book save(Book book) {
        return IBookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {

        Iterable<Book> all = IBookRepository.findAll();
        List<Book> bookList = new ArrayList<>();
        all.forEach(bookList::add);
        return bookList;

    }

    @Override
    public Book findBookById(int bookId) {
        return IBookRepository.findById(bookId).orElse(null);

    }

    @Override
    public String deleteBookById(int id) {
        IBookRepository.deleteById(id);
        return "Deleted";
    }
}
