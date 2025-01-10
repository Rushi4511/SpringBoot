package com.securityApp.SpringSecurity.advices;


import com.securityApp.SpringSecurity.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ApiError apiError = ApiError.builder()
                .error(exception.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    // Handle AuthenticationException
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException exception){
        ApiError apiError = ApiError.builder()
                .statusCode(HttpStatus.UNAUTHORIZED)
                .error(exception.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

// We can not handle JwtException(ExpiredJwtException and all child) using GlobalExceptionHandler
// because it can't handle exceptions that occurs before running Dispatcher
// Servlet so use HandlerExceptionResolver

    //Handle JwtException
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException exception){
        ApiError apiError = ApiError.builder()
                .statusCode(HttpStatus.UNAUTHORIZED)
                .error(exception.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex){

        ApiError apiError = ApiError.builder()
                .error(ex.getLocalizedMessage())
                .statusCode(HttpStatus.FORBIDDEN)
                .build();

        return new ResponseEntity<>(apiError,HttpStatus.FORBIDDEN);

    }
}
