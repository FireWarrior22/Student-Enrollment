package com.example.Student.Enrollment.Excpetions;

public class StudentEmailAlreadyExistsException extends RuntimeException{
    public StudentEmailAlreadyExistsException(String message){
        super(message);
    }
}
