package com.demo.library.assignment.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class BookStudentController {

    @PutMapping("AllocateBookToStudent")
    public String allocatedBook(@RequestParam(value = "studentId") int studentId,
                                          @RequestParam(value = "bookId") int bookId){
        return "working";
    }

    @PutMapping("UnAllocateBookToStudent")
    public String unallocatedBook(@RequestParam(value = "studentId") int studentId,
                                          @RequestParam(value = "bookId") int bookId){
        return "unallocated";
    }
}
