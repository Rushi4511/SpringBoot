package com.SpringTesting.advices;

import com.SpringTesting.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e){

        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<?> handleRuntimeExceptionException(RuntimeException e){

        return ResponseEntity.internalServerError().build();

    }

}
