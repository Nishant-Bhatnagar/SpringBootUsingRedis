package com.demo.library.assignment.services.impl;

import com.demo.library.assignment.model.Book;
import com.demo.library.assignment.model.Student;
import com.demo.library.assignment.repository.IBookRepository;
import com.demo.library.assignment.repository.IStudentRepository;
import com.demo.library.assignment.services.IStudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IStudentServiceImpl implements IStudentService {

    private IBookRepository bookRepository;
    private IStudentRepository studentRepository;
    IStudentServiceImpl(IBookRepository bookRepository, IStudentRepository studentRepository)
    {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public String save(Student student) {
        studentRepository.save(student);
        return "Saved";
    }

    @Override
    public List<Student> getAllStudents() {
        Iterable<Student> all = studentRepository.findAll();
        List<Student> studentList = new ArrayList<>();
        all.forEach(studentList :: add);
        return studentList;
    }

    @Override
    public Student findStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteStudentById(int id) {
        studentRepository.deleteById(id);
        return "Deleted";
    }

    @Override
    public String allocateBookToStudent(int studentId, int bookId) {
        Book book =  bookRepository.findById(bookId).orElse(null);
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
        bookRepository.save(book);
        return "Book allocated to student";
    }

    @Override
    public String unAlloactedBookToStudent(int studentId, int bookId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
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
        bookRepository.save(book);
        return "Book unallocated successfully";
    }
}
