package com.example.todo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false)
  private boolean completed = false;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column
  private LocalDateTime updatedAt;

  public Todo(String title) {
    this.title = title;
    this.createdAt = LocalDateTime.now();
  }

  public void toggleCompleted() { // 완료여부(토글)
    this.completed = !this.completed; // 반대로
    this.updatedAt = LocalDateTime.now();
  }

  public void updateTitle(String title) {
    this.title = title;
    this.updatedAt = LocalDateTime.now();
  }

  public void updateCompleted(boolean completed) {
    this.completed = completed;
    this.updatedAt = LocalDateTime.now();
  }
}
