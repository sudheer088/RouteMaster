package com.next.routeMaster.repository;

import com.next.routeMaster.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo  extends JpaRepository<Student,Long> {
}
