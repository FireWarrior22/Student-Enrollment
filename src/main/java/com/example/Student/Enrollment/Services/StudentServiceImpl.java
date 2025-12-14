package com.example.Student.Enrollment.Services;
import com.example.Student.Enrollment.Entities.Student;
import com.example.Student.Enrollment.Excpetions.StudentEmailAlreadyExistsException;
import com.example.Student.Enrollment.Excpetions.StudentNotFoundException;
import com.example.Student.Enrollment.Repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student createStudent(Student student) {
        if(studentDAO.findByEmail(student.getEmail()).isPresent())
            throw new StudentEmailAlreadyExistsException("Student email already exists");
       return studentDAO.save(student);
    }

    @Override
    public Student getStudentByID(int id) {
        return studentDAO.findById(id).orElseThrow(()-> new StudentNotFoundException("Student with id " + id + " not found"));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    @Override
    public List<Student> filterStudentByName(String name) {
        List<Student> tempStudentList = studentDAO.findByNameContainingIgnoreCase(name);
        if(tempStudentList.isEmpty())
            throw new StudentNotFoundException("No students found with the name" + name);

        return tempStudentList;
    }

    @Override
    public List<Student> filterStudentByAge(int age) {
        List<Student> tempStudentList = studentDAO.findAllByAge(age);
        if(tempStudentList.isEmpty())
            throw new StudentNotFoundException("No students found with the age greater than" + age);
        return tempStudentList;
    }

    @Override
    public Page<Student> filterStudentsByMinAge(int minAge, Pageable pageable) {
        Page<Student> page = studentDAO.findByAgeGreaterThanEqual(minAge, pageable);

        if (page.isEmpty()) {
            throw new StudentNotFoundException("No students found with age >= " + minAge);
        }

        return page;
    }

    @Override
    public void deleteStudent(int id) {
        if (!studentDAO.existsById(id)) throw new StudentNotFoundException("No student found with the ID: "+id);
        studentDAO.deleteById(id);
    }
}
