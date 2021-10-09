package com.ufitness.ufitness.exception;

public class EmailNotSendException extends RuntimeException{
    public EmailNotSendException(String message) {
        super(message);
    }
}
