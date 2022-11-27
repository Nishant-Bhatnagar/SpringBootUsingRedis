package com.demo.library.assignment.controller;

import com.demo.library.assignment.model.Student;
import com.demo.library.assignment.services.IStudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student/")
public class StudentController {
    private IStudentService studentService;

    StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("AddNewStudent")
    public String addStudent(@RequestBody Student student) {
        try {
            studentService.save(student);
            return "Student added";
        } catch (RuntimeException e) {
            return e.toString();
        }


    }

    @GetMapping("GetAllStudents")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Object getStudentById(@PathVariable int id) {
        return studentService.findStudentById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        return studentService.deleteStudentById(id);
    }
}
