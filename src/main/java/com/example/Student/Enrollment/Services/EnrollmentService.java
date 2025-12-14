package com.example.Student.Enrollment.Services;

import com.example.Student.Enrollment.Entities.Course;
import com.example.Student.Enrollment.Entities.Enrollment;
import com.example.Student.Enrollment.Entities.Student;

import java.util.List;

public interface EnrollmentService {

    public Enrollment enrollStudent(int studentID, int courseID);
    public List<Enrollment> getALlEnrollments();
    public List<Enrollment> getALlEnrollmentsForStudent(int studentID);
    public List<Enrollment> getAllEnrollmentsForCourse(int courseID);
    public Enrollment updateEnrollmentStatus(int enrollmentID, Enrollment.Status status);
    public Enrollment updateGrade(int enrollmentID, Enrollment.Grade grade);
    public boolean deleteEnrollment(int enrollmentID);

}
