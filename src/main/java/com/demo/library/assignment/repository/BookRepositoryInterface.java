package com.demo.library.assignment.repository;

import com.demo.library.assignment.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryInterface extends CrudRepository<Book, Integer> {
}
