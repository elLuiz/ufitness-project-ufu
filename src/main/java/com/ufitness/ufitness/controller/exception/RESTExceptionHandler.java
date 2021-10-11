package com.ufitness.ufitness.controller.exception;

import com.ufitness.ufitness.dto.RESTExceptionDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {
    // reference: https://www.baeldung.com/exception-handling-for-rest-with-spring
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String body = methodArgumentNotValidException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RESTExceptionDTO(HttpStatus.BAD_REQUEST.value(), body));
    }
}
