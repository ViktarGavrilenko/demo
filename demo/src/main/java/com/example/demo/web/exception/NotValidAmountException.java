package com.example.demo.web.exception;

public class NotValidAmountException extends RuntimeException {
    public NotValidAmountException(String message) {
        super(message);
    }
}