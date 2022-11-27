package com.demo.library.assignment.services;

import com.demo.library.assignment.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentServiceInterface {
    public String save(Student student);
    public List<Student> getAllStudents();
    public Student findStudentById(int id);
    public String deleteStudentById(int id);
    public String allocateBookToStudent(int studentId, int bookId);
    public String unAlloactedBookToStudent(int studentId, int bookId);
}
