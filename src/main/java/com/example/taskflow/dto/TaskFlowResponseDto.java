package com.example.taskflow.dto;
import jakarta.validation.constraints.Size;

import com.example.taskflow.model.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskFlowResponseDto {

	private Long id;
	@NotBlank
	private String title;
	@Size(max = 500, message = "Description must not exceed 500 characters")
	private String description;
	@NotNull(message = "Task status is required")
	private TaskStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

}
