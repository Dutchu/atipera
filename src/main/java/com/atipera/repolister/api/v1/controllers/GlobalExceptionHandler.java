package com.atipera.repolister.api.v1.controllers;

import com.atipera.repolister.api.v1.dto.ErrorResponse;
import com.atipera.repolister.services.exceptions.GitHubApiRateLimitException;
import com.atipera.repolister.services.exceptions.RepositoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RepositoryNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleRepositoryNotFound(RepositoryNotFoundException ex) {
        ErrorResponse errorBody = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({GitHubApiRateLimitException.class})
    public ResponseEntity<ErrorResponse> handleRateLimit(GitHubApiRateLimitException ex) {
        ErrorResponse errorBody = new ErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.FORBIDDEN);
    }
}
