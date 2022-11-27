package com.demo.library.assignment.repository;

import com.demo.library.assignment.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends CrudRepository<Book, Integer> {
}

