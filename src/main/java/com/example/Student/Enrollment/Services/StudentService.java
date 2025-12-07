package com.example.Student.Enrollment.Services;
import com.example.Student.Enrollment.Entities.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    public Student createStudent(Student student);
    public Student getStudentByID(int id);
    public List<Student> getAllStudents();
    public List<Student> filterStudentByName(String name);
    public List<Student> filterStudentByAge(int age);
    public boolean deleteStudent(int id);
}
