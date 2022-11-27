package com.demo.library.assignment.services.impl;

import com.demo.library.assignment.model.Book;
import com.demo.library.assignment.model.Student;
import com.demo.library.assignment.repository.BookRepositoryInterface;
import com.demo.library.assignment.repository.StudentRepositoryInterface;
import com.demo.library.assignment.services.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImplementation implements StudentServiceInterface {

   @Autowired
   BookRepositoryInterface bookRepositoryInterface;

   @Autowired
   StudentRepositoryInterface studentRepositoryInterface;
    @Override
    public String save(Student student) {
        studentRepositoryInterface.save(student);
        return "Saved";
    }

    @Override
    public List<Student> getAllStudents() {
        Iterable<Student> all = studentRepositoryInterface.findAll();
        List<Student> studentList = new ArrayList<>();
        all.forEach(studentList :: add);
        return studentList;
    }

    @Override
    public Student findStudentById(int id) {
        return studentRepositoryInterface.findById(id).orElse(null);
    }

    @Override
    public String deleteStudentById(int id) {
        studentRepositoryInterface.deleteById(id);
        return "Deleted";
    }

    @Override
    public String allocateBookToStudent(int studentId, int bookId) {
        Book book =  bookRepositoryInterface.findById(bookId).orElse(null);
        if(book.getAvailableCopies() < 1)
        {
            return "No copies available";
        }
        Student student = findStudentById(studentId);
        if(student.getAllocatedBook() >= 3)
        {
            return "Student Already have the maximum books";
        }
        student.setAllocatedBook(student.getAllocatedBook() + 1);
        save(student);
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepositoryInterface.save(book);
        return "Book allocated to student";
    }

    @Override
    public String unAlloactedBookToStudent(int studentId, int bookId) {
        Student student = studentRepositoryInterface.findById(studentId).orElse(null);
        Book book = bookRepositoryInterface.findById(bookId).orElse(null);
        if(student.getAllocatedBook() < 1)
        {
            return "No allocated book found";
        }
        if(book.getAvailableCopies() >= book.getTotalCopies())
        {
            return "No book allocated to students";
        }

        student.setAllocatedBook(student.getAllocatedBook() - 1);
        save(student);
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepositoryInterface.save(book);
        return "Book unallocated successfully";
    }
}
