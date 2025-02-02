package com.educandoweb.course.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class IllegalArgumentException extends RuntimeException {
    private static final long serialVersionUID = -6774160272022863110L;

    public IllegalArgumentException(String message) {
        super(message);
    }
}
