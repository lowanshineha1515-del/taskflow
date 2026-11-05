package com.example.taskflow.service;

import java.util.List;

import com.example.taskflow.dto.TaskFlowRequestDto;
import com.example.taskflow.dto.TaskFlowResponseDto;

public interface TaskFlowService {

	TaskFlowResponseDto createTask(TaskFlowRequestDto dto);

	List<TaskFlowResponseDto> getAllTasks();

	TaskFlowResponseDto getTaskById(long id);

	void updateTask(long id, TaskFlowRequestDto dto);

	void deleteTask(long id);

}
