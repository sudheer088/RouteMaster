package com.next.routeMaster.repository;

import com.next.routeMaster.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo  extends JpaRepository<Student,Long> {
}
