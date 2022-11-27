package com.demo.library.assignment.controller;

import com.demo.library.assignment.services.IStudentService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class BookStudentController {
    private IStudentService studentService;

    BookStudentController(IStudentService studentService) {
        this.studentService = studentService;
    }


    @PutMapping("AllocateBookToStudent")
    public String allocatedBook(@RequestParam(value = "studentId") int studentId,
                                @RequestParam(value = "bookId") int bookId) {
        return studentService.allocateBookToStudent(studentId, bookId);
    }

    @PutMapping("UnAllocateBookToStudent")
    public String unallocatedBook(@RequestParam(value = "studentId") int studentId,
                                  @RequestParam(value = "bookId") int bookId) {
        return studentService.unAlloactedBookToStudent(studentId, bookId);
    }
}
