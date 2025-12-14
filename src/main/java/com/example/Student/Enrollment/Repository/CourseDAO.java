package com.example.Student.Enrollment.Repository;

import com.example.Student.Enrollment.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDAO extends JpaRepository<Course,Integer> {

    List<Course> findByActive(boolean active);               // or findAllByActiveTrue()
    List<Course> findByCategoryIgnoreCase(String category);  // better: case-insensitive
    List<Course> findByCapacityGreaterThanEqual(int capacity);
}

