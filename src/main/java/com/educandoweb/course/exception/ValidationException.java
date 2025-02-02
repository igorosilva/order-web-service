package com.educandoweb.course.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 4881907519195158057L;

    public ValidationException(String message) {
        super(message);
    }
}
