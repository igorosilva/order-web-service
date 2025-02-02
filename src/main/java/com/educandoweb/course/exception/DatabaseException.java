package com.educandoweb.course.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper= true)
public class DatabaseException extends RuntimeException {

    private static final long serialVersionUID = -7145998083454878638L;

    public DatabaseException(String message) {
        super(message);
    }
}
