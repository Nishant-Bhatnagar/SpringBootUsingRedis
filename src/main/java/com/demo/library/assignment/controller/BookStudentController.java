package com.demo.library.assignment.controller;
import com.demo.library.assignment.services.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class BookStudentController {
    @Autowired
    private StudentServiceInterface studentServiceInterface;


    @PutMapping("AllocateBookToStudent")
    public String allocatedBook(@RequestParam(value = "studentId") int studentId,
                                          @RequestParam(value = "bookId") int bookId){
        return studentServiceInterface.allocateBookToStudent(studentId, bookId);
    }

    @PutMapping("UnAllocateBookToStudent")
    public String unallocatedBook(@RequestParam(value = "studentId") int studentId,
                                          @RequestParam(value = "bookId") int bookId){
        return studentServiceInterface.unAlloactedBookToStudent(studentId, bookId);
    }
}
