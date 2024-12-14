package com.example.demo.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotValidAmountException extends RuntimeException {
    @Getter
    private final HttpStatus httpStatus;

    public NotValidAmountException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}