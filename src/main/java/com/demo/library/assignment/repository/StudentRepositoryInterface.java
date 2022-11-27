package com.demo.library.assignment.repository;

import com.demo.library.assignment.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepositoryInterface extends CrudRepository<Student, Integer> {
}
