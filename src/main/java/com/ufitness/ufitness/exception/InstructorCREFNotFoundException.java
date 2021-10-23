package com.ufitness.ufitness.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InstructorCREFNotFoundException extends RuntimeException{
    public InstructorCREFNotFoundException() {
    }

    public InstructorCREFNotFoundException(String message) {
        super(message);
    }
}
