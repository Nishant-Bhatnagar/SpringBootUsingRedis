package com.demo.library.assignment.services.impl;

import com.demo.library.assignment.model.Book;
import com.demo.library.assignment.repository.IBookRepository;
import com.demo.library.assignment.services.IBookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IBookServiceImpl implements IBookService {

    private IBookRepository iBookRepository;
    IBookServiceImpl(IBookRepository iBookRepository){
        this.iBookRepository = iBookRepository;
    }
    @Override
    public Book save(Book book) {
        return iBookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {

        Iterable<Book> all = iBookRepository.findAll();
        List<Book> bookList = new ArrayList<>();
        all.forEach(bookList::add);
        return bookList;

    }

    @Override
    public Book findBookById(int bookId) {
        return iBookRepository.findById(bookId).orElse(null);

    }

    @Override
    public String deleteBookById(int id) {
        iBookRepository.deleteById(id);
        return "Deleted";
    }
}
