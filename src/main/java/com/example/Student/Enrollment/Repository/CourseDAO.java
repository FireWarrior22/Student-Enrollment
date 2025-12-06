package com.example.Student.Enrollment.Repository;

import com.example.Student.Enrollment.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDAO extends JpaRepository<Course,Integer> {
}
