package com.educandoweb.course.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
public class GlobalExceptionHandler {
    private String error;
    private HttpStatus status;
    private StandarException exception;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandarException> notFound(NotFoundException e, HttpServletRequest request) {
        error = "Resource not found";
        status = NOT_FOUND;
        exception = createStandarException(status, error, e.getMessage(), request);

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandarException> database(DatabaseException e, HttpServletRequest request) {
        error = "Database error";
        status = BAD_REQUEST;
        exception = createStandarException(status, error, e.getMessage(), request);

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandarException> business(BusinessException e, HttpServletRequest request) {
        error = "Business error";
        status = UNPROCESSABLE_ENTITY;
        exception = createStandarException(status, error, e.getMessage(), request);

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandarException> validation(ValidationException e, HttpServletRequest request) {
        error = "Validation error";
        status = UNPROCESSABLE_ENTITY;
        exception = createStandarException(status, error, e.getMessage(), request);

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandarException> illegalArgument(ValidationException e, HttpServletRequest request) {
        error = "Validation error";
        status = BAD_REQUEST;
        exception = createStandarException(status, error, e.getMessage(), request);

        return ResponseEntity.status(status).body(exception);
    }

    private StandarException createStandarException(HttpStatus status, String error, String message, HttpServletRequest request) {
        return new StandarException(status, error, message, request.getRequestURI());
    }
}
