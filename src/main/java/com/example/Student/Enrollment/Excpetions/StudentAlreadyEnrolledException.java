package com.example.Student.Enrollment.Excpetions;

public class StudentAlreadyEnrolledException extends RuntimeException{

    public StudentAlreadyEnrolledException(String message){
        super(message);
    }
}
