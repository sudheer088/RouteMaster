package com.next.routeMaster.service;

import com.next.routeMaster.entity.Student;
import com.next.routeMaster.repository.StudentRepo;

import java.util.List;

public class StudentService {
    private StudentRepo studentRepo;
    public StudentService(StudentRepo studentrepo){
        this.studentRepo= studentrepo;
    }
    public Student  createStudent(Student student){
        return studentRepo.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }



}
