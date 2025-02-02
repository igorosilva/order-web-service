package com.educandoweb.course.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -7431016337932215565L;

    public BusinessException(String mensagem) {
        super(mensagem);
    }
}
