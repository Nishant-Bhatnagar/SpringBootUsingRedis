package com.demo.library.assignment.repository;

import com.demo.library.assignment.model.Book;
import com.demo.library.assignment.model.Student;
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
    public Object findStudentById(int id){
        return template.opsForHash().get(HASH_KEY, id);
    }
    public String deleteStudentById(int id){
        template.opsForHash().delete(HASH_KEY, id);
        return "Student removed successfully";
    }
}
