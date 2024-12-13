package com.example.demo.web.exception;

public class UserNoFoundException extends RuntimeException {
    public UserNoFoundException(String message) {
        super(message);
    }
}
