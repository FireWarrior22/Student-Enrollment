package com.example.Student.Enrollment.Services;
import com.example.Student.Enrollment.Entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    public Student createStudent(Student student);
    public Student getStudentByID(int id);
    public List<Student> getAllStudents();
    public List<Student> filterStudentByName(String name);
    public List<Student> filterStudentByAge(int age);
    public void deleteStudent(int id);
    Page<Student> filterStudentsByMinAge(int minAge, Pageable pageable);
}
