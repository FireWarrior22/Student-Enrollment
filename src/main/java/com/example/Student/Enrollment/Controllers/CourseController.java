package com.example.Student.Enrollment.Controllers;

import com.example.Student.Enrollment.Entities.Course;
import com.example.Student.Enrollment.Services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course){
        Course created = courseService.createCourse(course);
        URI location = URI.create("/course/id/" + created.getId());
        return ResponseEntity.created(location).body(created); // 201 Created
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity.ok(courseService.getALlCourses());
    }

    @GetMapping("/{courseID}")
    public ResponseEntity<Course> getCourse(@PathVariable int courseID){
        return ResponseEntity.ok(courseService.getCourseByID(courseID));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Course>> getCourseByCategory(@RequestParam String category){
        return ResponseEntity.ok(courseService.getCourseByCategory(category));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Course>> getActiveCourses(@RequestParam boolean active){
        return ResponseEntity.ok(courseService.getAllActiveCourse(active));
    }

    @GetMapping("/capacity")
    public ResponseEntity<List<Course>> getCoursesByCapacity(@RequestParam int capacity){
        return ResponseEntity.ok(courseService.getCourseByCapacity(capacity));
    }

    @DeleteMapping("/{courseID}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int courseID){
        courseService.deleteCourse(courseID);
        return ResponseEntity.noContent().build();
    }

}
