package com.example.todo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

  // 404
  @ExceptionHandler(TodoNotFoundException.class)
  public ResponseEntity<ErrorResponse> errorResponse(TodoNotFoundException e) {
    ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());

    return ResponseEntity.status(404).body(errorResponse);
  }

  // 400
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> errorResponse(MethodArgumentNotValidException e) {
    String message = e.getBindingResult().getFieldError().getDefaultMessage();
    ErrorResponse errorResponse = new ErrorResponse(400, message);

    return ResponseEntity.status(400).body(errorResponse);
  }
}