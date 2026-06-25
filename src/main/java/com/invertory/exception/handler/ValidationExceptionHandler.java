package com.invertory.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleValidationException(MethodArgumentNotValidException e){
        log.info(e.getFieldError().getDefaultMessage());
    }
}
