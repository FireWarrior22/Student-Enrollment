package com.example.Student.Enrollment.Repository;
import com.example.Student.Enrollment.Entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentDAO extends JpaRepository<Enrollment,Integer> {
}
