package com.example.Student.Enrollment.Entities;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToMany
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "enrolled_at", nullable = false)
    private LocalDateTime enrolledAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade")
    private Grade grade;

    public enum Status {
        ENROLLED,
        COMPLETED,
        DROPPED
    }

    public enum Grade {
        A, B, C, D, F
    }

    public Enrollment(Student student, Course course, LocalDateTime enrolledAt, Status status, Grade grade) {
        this.student = student;
        this.course = course;
        this.enrolledAt = enrolledAt;
        this.status = status;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
