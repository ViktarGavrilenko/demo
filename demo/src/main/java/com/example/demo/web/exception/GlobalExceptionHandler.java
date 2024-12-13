package com.example.demo.web.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNoFoundException.class)
    public ErrorInfo handlerUserNotFoundException(UserNoFoundException e) {
        log.error(e.getMessage(), e);
        return new ErrorInfo(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(NotValidAmountException.class)
    public ErrorInfo handlerNotValidAmountException(NotValidAmountException e) {
        log.error(e.getMessage(), e);
        return new ErrorInfo(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Exception.class)
    public ErrorInfo applicationError(Exception e) {
        log.error(e.getMessage(), e);
        return new ErrorInfo(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}