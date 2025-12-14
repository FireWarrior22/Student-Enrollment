package com.example.Student.Enrollment.Repository;

import com.example.Student.Enrollment.Entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentDAO extends JpaRepository<Student,Integer> {

    List<Student> findByNameContainingIgnoreCase(String name);
    public List<Student> findAllByAge(int age);
    Page<Student> findByAgeGreaterThanEqual(int age, Pageable pageable);
    Optional<Student> findByEmail(String email);
}
