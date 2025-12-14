package com.example.Student.Enrollment.Excpetions;

public class EnrollmentNotFoundException extends RuntimeException{

    public EnrollmentNotFoundException(String message){
        super(message);
    }
}
