package com.example.Student.Enrollment.Excpetions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(String message){
        super(message);
    }

}
