package com.demo.library.assignment.repository;

import com.demo.library.assignment.model.Book;
import com.demo.library.assignment.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    public static final String HASH_KEY = "student";
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    @Autowired
    private BookRepository bookRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public String save(Student student){
        if(student.getAllocatedBook() > 3)
        {
            return "exceed the max limit";
        }
        template.opsForHash().put(HASH_KEY,student.getId(), student);
        return "Student Added";
    }

    public List<Student> getAllStudents(){
        return template.opsForHash().values(HASH_KEY);
    }
    public Student findStudentById(int id){
        Object object = template.opsForHash().get(HASH_KEY, id);
       Student student = objectMapper.convertValue(object, Student.class);
        return student;
    }
    public String deleteStudentById(int id){
        template.opsForHash().delete(HASH_KEY, id);
        return "Student removed successfully";
    }

    public String allocateBookToStudent(int studentId, int bookId){
       Book book =  bookRepository.findBookById(bookId);
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
    public String unAlloactedBookToStudent(int studentId, int bookId){
        Student student = findStudentById(studentId);
        Book book = bookRepository.findBookById(bookId);
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
