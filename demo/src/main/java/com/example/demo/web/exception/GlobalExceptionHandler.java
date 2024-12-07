package com.example.demo.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    @ExceptionHandler(Exception.class)
    public ErrorInfo applicationError(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        log.error(e.getMessage(), e);
        return new ErrorInfo(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}