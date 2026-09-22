package com.example.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoUpdateRequest {
  @NotBlank(message = "할 일을 입력해주세요.")
  @Size(max = 100, message = "할 일은 100자를 넘을 수 없습니다.")
  private String title;

  private boolean completed;

  public TodoUpdateRequest(String title, boolean completed) {
   this.title = title;
   this.completed = completed;
  }
}