package com.demo.library.assignment.controller;

import com.demo.library.assignment.model.Student;
import com.demo.library.assignment.services.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentServiceInterface studentServiceInterface;

    @PostMapping("/AddNewStudent")
    public String addStudent(@RequestBody Student student)
    {

        return studentServiceInterface.save(student);
    }

    @GetMapping("/GetAllStudents")
    public List<Student> getAllStudents()
    {
        return studentServiceInterface.getAllStudents();
    }
    @GetMapping("/{id}")
    public Object getStudentById(@PathVariable int id)
    {
        return studentServiceInterface.findStudentById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id){
        return studentServiceInterface.deleteStudentById(id);
    }
}
