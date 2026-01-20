package com.next.routeMaster.entity;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String grade;
    @Column(nullable = false,length=10)
    private String parent_number;

    public Student() {
    }

    public Student(String name, String parent_number,String grade) {
        this.name = name;
        this.parent_number = parent_number;
        this.grade=grade;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_number() {
        return parent_number;
    }

    public void setParent_number(String parent_number) {
        this.parent_number = parent_number;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
