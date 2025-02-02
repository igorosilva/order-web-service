package com.educandoweb.course.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 8973877003662748181L;

    public NotFoundException(Object id) {
        super("Resource not found with id: " + id);
    }
}
