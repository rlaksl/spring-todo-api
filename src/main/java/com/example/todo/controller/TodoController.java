package com.example.todo.controller;

import com.example.todo.dto.TodoCreateRequest;
import com.example.todo.dto.TodoResponse;
import com.example.todo.dto.TodoUpdateRequest;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @PostMapping // * * 생성 * *
  public TodoResponse createTodo(@Valid @RequestBody TodoCreateRequest request) {
    return  todoService.createTodo(request);
  }

  @GetMapping // * * 목록 조회 * *
  public List<TodoResponse> getTodos() {
    return todoService.getTodos();
  }

  @GetMapping("/{id}") // * * 개별 조회 * *
  public TodoResponse getTodo(@PathVariable Long id) {
    return todoService.getTodo(id);
  }

  @PutMapping("/{id}")  // * * 수정 * *
  public TodoResponse update(@PathVariable Long id, @Valid @RequestBody TodoUpdateRequest request) {
    return todoService.updateTodo(id, request);
  }

  @DeleteMapping("/{id}") // * * 삭제 * *
  public void deleteTodo(@PathVariable Long id) {
    todoService.deleteTodo(id);
  }
}