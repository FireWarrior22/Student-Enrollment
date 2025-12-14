package com.example.Student.Enrollment.Excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> handleStudentNotFound(StudentNotFoundException ex){

        Map<String, Object> body=new HashMap<>();
        body.put("timeStamp ", LocalDateTime.now());
        body.put("error ", "Student not found");
        body.put("message ", ex.getMessage());
        body.put("status ", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Object> handleCourseNotFound(CourseNotFoundException ex){

        Map<String,Object> body=new HashMap<>();

        body.put("timestamp ", LocalDateTime.now());
        body.put("error ", "Course not found");
        body.put("message ", ex.getMessage());
        body.put("status ", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<Object> handleEnrollmentNotFoundException(EnrollmentNotFoundException ex){

        Map<String,Object> body = new HashMap<>();

        body.put("timestamp ", LocalDateTime.now());
        body.put("error ", "No enrollments found for the {studentID, courseID)");
        body.put("message ", ex.getMessage());
        body.put("status ", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(StudentEmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleStudentEmailAlreadyExistsException(StudentEmailAlreadyExistsException ex){

        Map<String,Object> body = new HashMap<>();

        body.put("timestamp ", LocalDateTime.now());
        body.put("error ", "Student email already exists");
        body.put("message ", ex.getMessage());
        body.put("status ", HttpStatus.CONFLICT);

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentAlreadyEnrolledException.class)
    public ResponseEntity<Object> handleStudentAlreadyEnrolledException(StudentAlreadyEnrolledException ex){

        Map<String,Object> body = new HashMap<>();

        body.put("timestamp",LocalDateTime.now());
        body.put("error","Student already enrolled");
        body.put("message",ex.getMessage());
        body.put("status",HttpStatus.CONFLICT);

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(CourseCapacityExceededException.class)
    public ResponseEntity<Object> handleCourseCapacityExceededExceptionException(CourseCapacityExceededException ex){

        Map<String,Object> body = new HashMap<>();

        body.put("timestamp",LocalDateTime.now());
        body.put("error","Course capacity reached.. Please try enrolling a different course");
        body.put("message",ex.getMessage());
        body.put("status",HttpStatus.CONFLICT);

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);

    }


}
