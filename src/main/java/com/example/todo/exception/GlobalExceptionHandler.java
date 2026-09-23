package com.example.todo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(TodoNotFoundException.class)
  public ResponseEntity<ErrorResponse> errorResponse(TodoNotFoundException e) {

    ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
    return ResponseEntity.status(404).body(errorResponse);
  }
}