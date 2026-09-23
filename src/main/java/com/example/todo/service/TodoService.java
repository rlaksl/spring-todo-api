package com.example.todo.service;

import com.example.todo.dto.TodoCreateRequest;
import com.example.todo.dto.TodoResponse;
import com.example.todo.dto.TodoUpdateRequest;
import com.example.todo.entity.Todo;
import com.example.todo.exception.TodoNotFoundException;
import com.example.todo.repository.TodoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TodoService {
  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public TodoResponse createTodo(TodoCreateRequest request) {
    Todo todo = new Todo(request.getTitle());
    Todo savedTodo = todoRepository.save(todo);

    return new TodoResponse(savedTodo);
  }

  public List<TodoResponse> getTodos() {
    List<Todo> todos = todoRepository.findAll();

    return todos.stream()
            .map(todo -> new TodoResponse(todo))
            .toList();
  }

  public TodoResponse getTodo(Long id) {
    Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new TodoNotFoundException(id)); // 목록에 없으면 예외

    return new TodoResponse(todo);
  }

  public TodoResponse updateTodo(Long id, TodoUpdateRequest request) {
    Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new TodoNotFoundException(id));

    todo.updateTitle(request.getTitle());
    todo.updateCompleted(request.isCompleted());

    return new TodoResponse(todo);
  }

  public void deleteTodo(Long id) {
    if(!todoRepository.existsById(id)) {
      throw new TodoNotFoundException(id);
    }
    todoRepository.deleteById(id);
  }
}

