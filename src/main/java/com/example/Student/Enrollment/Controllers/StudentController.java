package com.example.Student.Enrollment.Controllers;

import com.example.Student.Enrollment.Entities.Student;
import com.example.Student.Enrollment.Services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student savedStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }


    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{studentID}")
    public ResponseEntity<Student> getStudentByID(@PathVariable int studentID){
        return ResponseEntity.ok(studentService.getStudentByID(studentID));
    }

    @GetMapping("/search/by-Name")
    public ResponseEntity<List<Student>> getStudentsByName(@RequestParam String name){
        return ResponseEntity.ok(studentService.filterStudentByName(name));
    }

    @GetMapping("/search/by-Age")
    public ResponseEntity<List<Student>> getStudentsByAge(@RequestParam int age){
        return ResponseEntity.ok(studentService.filterStudentByAge(age));
    }

    @GetMapping("/age")
    public ResponseEntity<Page<Student>> getStudentsByMinAge(
            @RequestParam int minAge,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        return ResponseEntity.ok(studentService.filterStudentsByMinAge(minAge, pageable));
    }

    @DeleteMapping("/{studentID}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentID){
        studentService.deleteStudent(studentID);
        return ResponseEntity.noContent().build();
    }


}
