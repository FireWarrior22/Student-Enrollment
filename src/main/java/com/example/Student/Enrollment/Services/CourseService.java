package com.example.Student.Enrollment.Services;

import com.example.Student.Enrollment.Entities.Course;
import java.util.List;


public interface CourseService {

    public Course createCourse(Course course);
    public Course getCourseByID(int id);
    public List<Course> getALlCourses();
    public List<Course> getAllActiveCourse(boolean active);
    public List<Course> getCourseByCategory(String category);
    public List<Course> getCourseByCapacity(int capacity);
    public boolean deleteCourse(int id);

}
