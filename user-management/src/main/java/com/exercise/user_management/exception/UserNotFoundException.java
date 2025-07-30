package com.exercise.user_management.exception;

public class UserNotFoundException extends AppException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
