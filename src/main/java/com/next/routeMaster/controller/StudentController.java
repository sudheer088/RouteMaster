package com.next.routeMaster.controller;

import com.next.routeMaster.entity.Student;
import com.next.routeMaster.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService  studentService;
    @PostMapping("/add")
        public Student CreateStudent (@RequestBody Student student){
        return studentService.createStudent(student);

        }
        @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();

        }
    }

