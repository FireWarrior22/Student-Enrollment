package com.example.Student.Enrollment.Services;
import com.example.Student.Enrollment.Entities.Course;
import com.example.Student.Enrollment.Entities.Enrollment;
import com.example.Student.Enrollment.Entities.Student;
import com.example.Student.Enrollment.Excpetions.CourseCapacityExceededException;
import com.example.Student.Enrollment.Excpetions.EnrollmentNotFoundException;
import com.example.Student.Enrollment.Excpetions.StudentAlreadyEnrolledException;
import com.example.Student.Enrollment.Repository.EnrollmentDAO;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class EnrollmentServiceImpl implements EnrollmentService{

   private final EnrollmentDAO enrollmentDAO;
   private final StudentService studentService;
   private final CourseService courseService;

    public EnrollmentServiceImpl(EnrollmentDAO enrollmentDAO, StudentService studentService, CourseService courseService) {
        this.enrollmentDAO = enrollmentDAO;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public Enrollment enrollStudent(int studentID, int courseID) {
        Enrollment enrollment = new Enrollment();
        Student student = studentService.getStudentByID(studentID);
        Course course = courseService.getCourseByID(courseID);
        long enrolledCount = enrollmentDAO.countByCourseAndStatus(course, Enrollment.Status.ENROLLED);

        if(enrollmentDAO.findByStudentAndCourse(student,course).isPresent())
            throw new StudentAlreadyEnrolledException("Student "+student.getName()+" has already enrolled in course "+course.getTitle());

        if (enrolledCount >= course.getCapacity()) {
            throw new CourseCapacityExceededException("Course capacity reached.. Please try enrolling a different course");
        }
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollment.setEnrolledAt(LocalDateTime.now());
            enrollment.setStatus(Enrollment.Status.ENROLLED);
            enrollment.setGrade(Enrollment.Grade.A);
            return enrollmentDAO.save(enrollment);
    }

    @Override
    public List<Enrollment> getALlEnrollments() {
        return enrollmentDAO.findAll();
    }

    @Override
    public List<Enrollment> getALlEnrollmentsForStudent(int studentID) {

        Student student = studentService.getStudentByID(studentID);
        List<Enrollment> enrollments = enrollmentDAO.findByStudent(student);

        if(enrollments.isEmpty())
            throw new EnrollmentNotFoundException("No enrollments found for the studentID: "+studentID);

        return enrollments;
    }

    @Override
    public List<Enrollment> getAllEnrollmentsForCourse(int courseID) {
        Course course = courseService.getCourseByID(courseID);
        List<Enrollment> enrollments = enrollmentDAO.findByCourse(course);

        if(enrollments.isEmpty())
            throw new EnrollmentNotFoundException("No enrollments found for the courseID: "+courseID);

        return enrollments;
    }

    @Override
    public Enrollment updateEnrollmentStatus(int enrollmentID, Enrollment.Status status) {
        Enrollment enrollment = enrollmentDAO.findById(enrollmentID).orElseThrow(() -> new EnrollmentNotFoundException("No enrollments found with the ID: " + enrollmentID));
        enrollment.setStatus(status);
        return enrollmentDAO.save(enrollment);
    }

    @Override
    public Enrollment updateGrade(int enrollmentID, Enrollment.Grade grade) {
        Enrollment enrollment = enrollmentDAO.findById(enrollmentID).orElseThrow(()->new EnrollmentNotFoundException("No enrollments found with the ID: "+enrollmentID));
        enrollment.setGrade(grade);
        return enrollmentDAO.save(enrollment);
    }

    @Override
    public boolean deleteEnrollment(int enrollmentID) {
        if(!enrollmentDAO.existsById(enrollmentID))
            throw new EnrollmentNotFoundException("No enrollments found with the ID:"+enrollmentID);
        enrollmentDAO.deleteById(enrollmentID);
        return true;
    }
}
