package com.demo.library.assignment.repository;

import com.demo.library.assignment.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface IStudentRepository extends CrudRepository<Student, Integer> {
}
