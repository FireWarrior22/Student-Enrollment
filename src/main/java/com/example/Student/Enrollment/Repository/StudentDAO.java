package com.example.Student.Enrollment.Repository;

import com.example.Student.Enrollment.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO extends JpaRepository<Student,Integer> {


}
