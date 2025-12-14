package com.example.Student.Enrollment.DTOs;
import jakarta.validation.constraints.NotNull;

public class EnrollmentRequestDTO {

    @NotNull(message = "studentID is required")
    private Integer studentID;

    @NotNull(message = "courseID is required")
    private Integer courseID;

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentId) {
        this.studentID = studentId;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseId) {
        this.courseID = courseId;
    }
}
