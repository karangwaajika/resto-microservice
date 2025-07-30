package com.exercise.user_management.exception;

public class UserExistsException extends AppException{
    public UserExistsException(String message) {
        super(message);
    }
}
