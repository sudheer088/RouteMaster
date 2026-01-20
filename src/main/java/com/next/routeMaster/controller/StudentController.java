package com.next.routeMaster.controller;

import com.next.routeMaster.entity.Student;
import com.next.routeMaster.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService  studentService;
    @PostMapping("/add")
        public String CreateStudent (@RequestBody Student student){
         studentService.createStudent(student);
         return "student saved";

        }
        @GetMapping("/all")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();

        }
    }

