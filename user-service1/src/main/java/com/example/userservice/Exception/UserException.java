package com.example.userservice.Exception;

public class UserException extends RuntimeException{

    public static UserException USER_NOT_ENABLED = new UserException("User is not enabled");

    public static UserException USER_EXISTS = new UserException("User exists in the database");

    public UserException(String message) {
        super(message);
    }
}
