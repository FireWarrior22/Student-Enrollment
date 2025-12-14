package com.example.Student.Enrollment.Excpetions;

public class CourseCapacityExceededException extends RuntimeException{
    public CourseCapacityExceededException(String message){
        super(message);
    }
}
