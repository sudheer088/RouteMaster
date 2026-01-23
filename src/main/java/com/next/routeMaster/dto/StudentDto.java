package com.next.routeMaster.dto;

public class StudentDto {
     private Long  studentId;
     private String name;
     private String grade;
     private String phone;

     public StudentDto(Long studentId, String name, String grade, String phone) {
          this.studentId = studentId;
          this.name = name;
          this.grade = grade;
          this.phone = phone;
     }

     public Long getStudentId() {
          return studentId;
     }

     public void setStudentId(Long studentId) {
          this.studentId = studentId;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getGrade() {
          return grade;
     }

     public void setGrade(String grade) {
          this.grade = grade;
     }

     public String getPhone() {
          return phone;
     }

     public void setPhone(String phone) {
          this.phone = phone;
     }
}
