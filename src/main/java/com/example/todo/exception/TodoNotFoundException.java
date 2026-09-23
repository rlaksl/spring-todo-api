package com.example.todo.exception;

public class TodoNotFoundException extends RuntimeException {
  public TodoNotFoundException(Long id) {
    super("id "+ id +"번에 해당하는 할 일을 찾을 수 없습니다.");
  }
}