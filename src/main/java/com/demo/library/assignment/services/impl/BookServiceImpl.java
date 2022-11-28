package com.demo.library.assignment.services.impl;

import com.demo.library.assignment.model.Book;
import com.demo.library.assignment.repository.IBookRepository;
import com.demo.library.assignment.services.IBookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    private IBookRepository iBookRepository;

    BookServiceImpl(IBookRepository iBookRepository) {
        this.iBookRepository = iBookRepository;
    }

    @Override
    public Book save(Book book) {
        return iBookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {

        Iterable<Book> bookIterable = iBookRepository.findAll();
        List<Book> bookList = new ArrayList<>();
        bookIterable.forEach(bookList::add);
        return bookList;

    }

    @Override
    public Book findBookById(int bookId) {
        return iBookRepository.findById(bookId).orElse(null);

    }

    @Override
    public String deleteBookById(int id) {
        Book book = findBookById(id);
        if (null != book) {
            iBookRepository.deleteById(id);
            return "Deleted";
        }
        return "Book not found";

    }
}
