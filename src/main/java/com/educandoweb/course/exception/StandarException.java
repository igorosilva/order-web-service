package com.educandoweb.course.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandarException implements Serializable {

    private static final long serialVersionUID = -1405414026261303092L;

    private HttpStatus status;
    private String error;
    private String message;
    private String path;
}
