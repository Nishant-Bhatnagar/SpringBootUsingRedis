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
public class StudentServiceImpl implements IStudentService {

    private IBookRepository bookRepository;
    private IStudentRepository studentRepository;

    StudentServiceImpl(IBookRepository bookRepository, IStudentRepository studentRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        Iterable<Student> studentIterable = studentRepository.findAll();
        List<Student> studentList = new ArrayList<>();
        studentIterable.forEach(studentList::add);
        return studentList;
    }

    @Override
    public Student findStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteStudentById(int id) {
        Student student = findStudentById(id);
        if (null != student) {
            studentRepository.deleteById(id);
            return "Deleted";
        }
        return "Student not found";

    }

    @Override
    public String allocateBookToStudent(int studentId, int bookId) {
        Student student = findStudentById(studentId);
        Book book = bookRepository.findById(bookId).orElse(null);
        if (isBookAvailable(book) && isStudentEligible(student)) {
            student.setAllocatedBook(student.getAllocatedBook() + 1);
            save(student);
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
            return "Book allotted to student";
        }
        return "Error cause due to one of the reasons:" +
                "Student is not Eligible  or Book is not available";

    }

    private boolean isBookAvailable(Book book) {
        if (null != book && book.getAvailableCopies() > 1) {
            return true;
        }
        return false;
    }

    private boolean isStudentEligible(Student student) {
        if (null != student && student.getAllocatedBook() < 3) {
            return true;
        }
        return false;

    }

    @Override
    public String unAlloactedBookToStudent(int studentId, int bookId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        if (isCopyOfBookAvailable(book, bookId) && isStudentHaveBook(student, studentId)) {
            student.setAllocatedBook(student.getAllocatedBook() - 1);
            save(student);
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            bookRepository.save(book);
            return "Book unallocated successfully";
        }


        return "Student does not have any book allotted";
    }

    private boolean isCopyOfBookAvailable(Book book, int bookId) {
        if (null != book && (book.getAvailableCopies() < book.getTotalCopies())) {
            return true;
        }
        return false;
    }

    private boolean isStudentHaveBook(Student student, int studentId) {
        if (null != student && student.getAllocatedBook() > 0) {
            return true;
        }
        return false;

    }
}
