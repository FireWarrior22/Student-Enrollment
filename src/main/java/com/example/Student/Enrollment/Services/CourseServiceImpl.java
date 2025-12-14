package com.example.Student.Enrollment.Services;

import com.example.Student.Enrollment.Entities.Course;
import com.example.Student.Enrollment.Excpetions.CourseNotFoundException;
import com.example.Student.Enrollment.Repository.CourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseDAO courseDAO;

    @Autowired
    public CourseServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public Course createCourse(Course course) {
        return courseDAO.save(course);
    }

    @Override
    public Course getCourseByID(int id) {
        return courseDAO.findById(id).orElseThrow(()->new CourseNotFoundException("No courses found with ID: "+id));
    }

    @Override
    public List<Course> getALlCourses() {
        List<Course> courses = courseDAO.findAll();
        if(courses.isEmpty())
            throw new CourseNotFoundException("No courses found");
        return courses;
    }

    @Override
    public List<Course> getAllActiveCourse(boolean active) {
        List<Course> courses = courseDAO.findByActive(active);
        if(courses.isEmpty())
            throw new CourseNotFoundException("No courses found");
        return courses;
    }

    @Override
    public List<Course> getCourseByCategory(String category) {
        List<Course> courses = courseDAO.findByCategoryIgnoreCase(category);
        if(courses.isEmpty())
            throw new CourseNotFoundException("No courses found");
        return courses;
    }

    @Override
    public List<Course> getCourseByCapacity(int capacity) {
        List<Course> courses = courseDAO.findByCapacityGreaterThanEqual(capacity);
        if(courses.isEmpty())
            throw new CourseNotFoundException("No courses found");
        return courses;
    }

    @Override
    public boolean deleteCourse(int id) {
        if(!courseDAO.existsById(id))
            throw new CourseNotFoundException("No course found with id: "+id);
        courseDAO.deleteById(id);
        return true;
    }
}
