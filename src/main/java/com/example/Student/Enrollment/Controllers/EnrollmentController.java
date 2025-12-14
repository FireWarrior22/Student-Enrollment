package com.example.Student.Enrollment.Controllers;

import com.example.Student.Enrollment.DTOs.EnrollmentRequestDTO;
import com.example.Student.Enrollment.Entities.Enrollment;
import com.example.Student.Enrollment.Services.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    final private EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<Enrollment> enrollStudent(@Valid @RequestBody EnrollmentRequestDTO request){
        Enrollment enrollment = enrollmentService.enrollStudent(request.getStudentID(),request.getCourseID());
        URI location = URI.create("/enrollments/" + enrollment.getId());
        return ResponseEntity.created(location).body(enrollment);
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>>getAllEnrollments(){
        return ResponseEntity.ok(enrollmentService.getALlEnrollments());
    }

    @GetMapping("/student/{studentID}")
    public ResponseEntity<List<Enrollment>>getAllEnrollmentsForStudent(@PathVariable int studentID){
        return ResponseEntity.ok(enrollmentService.getALlEnrollmentsForStudent(studentID));
    }

    @GetMapping("/course/{courseID}")
    public ResponseEntity<List<Enrollment>> getAllEnrollmentForCourse(@PathVariable int courseID){
        return ResponseEntity.ok(enrollmentService.getAllEnrollmentsForCourse(courseID));
    }

    @PutMapping("/{enrollmentID}/status/{status}")
    public ResponseEntity<Enrollment> updateEnrollmentStatus(@PathVariable int enrollmentID, @RequestParam Enrollment.Status status){
        return ResponseEntity.ok(enrollmentService.updateEnrollmentStatus(enrollmentID, status));
    }

    @PutMapping("/{enrollmentID}/grade/{grade}")
    public ResponseEntity<Enrollment> updateGrade(@PathVariable int enrollmentID, @RequestParam Enrollment.Grade grade){
        return ResponseEntity.ok(enrollmentService.updateGrade(enrollmentID, grade));
    }

    @DeleteMapping("/{enrollmentID}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable int enrollmentID){
       enrollmentService.deleteEnrollment(enrollmentID);
       return ResponseEntity.noContent().build();
    }
}
