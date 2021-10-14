package com.ufitness.ufitness.controller.exception;

import com.ufitness.ufitness.dto.RESTExceptionDTO;
import com.ufitness.ufitness.exception.EmailNotSendException;
import com.ufitness.ufitness.exception.InvalidTokenException;
import com.ufitness.ufitness.exception.LoginException;
import com.ufitness.ufitness.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String body = methodArgumentNotValidException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RESTExceptionDTO(HttpStatus.BAD_REQUEST.value(), body));
    }

    @ExceptionHandler(EmailNotSendException.class)
    protected ResponseEntity<Object> handleEmailException(EmailNotSendException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handlerUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", ex.getMessage()));
    }

    @ExceptionHandler(LoginException.class)
    protected ResponseEntity<Object> handlerLoginException(LoginException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", ex.getMessage()));
    }

    @ExceptionHandler(InvalidTokenException.class)
    protected ResponseEntity<Object> handlerInvalidTokenException(InvalidTokenException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", ex.getMessage()));
    }
}
