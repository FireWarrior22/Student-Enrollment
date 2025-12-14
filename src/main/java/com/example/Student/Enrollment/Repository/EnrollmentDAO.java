package com.example.Student.Enrollment.Repository;
import com.example.Student.Enrollment.Entities.Course;
import com.example.Student.Enrollment.Entities.Enrollment;
import com.example.Student.Enrollment.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentDAO extends JpaRepository<Enrollment, Integer> {

    // all enrollments of a given student
    List<Enrollment> findByStudent(Student student);
    // all enrollments of a given course
    List<Enrollment> findByCourse(Course course);
    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);
    long countByCourseAndStatus(Course course, Enrollment.Status status);

}

