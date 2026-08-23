package com.invertory.exception.handler;

import com.invertory.enums.ErrorMessage;
import com.invertory.exception.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.info(e.getFieldError().getDefaultMessage());
        ErrorResponse response = new ErrorResponse();

        response.setError(ErrorMessage.REGISTRATION_FAILED.toString());
        response.setMessage(e.getFieldError().getDefaultMessage());
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setPath(request.getRequestURI());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
