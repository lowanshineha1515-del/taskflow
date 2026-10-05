package com.example.taskflow.service;

import com.example.taskflow.dto.TaskFlowRequestDto;
import com.example.taskflow.dto.TaskFlowResponseDto;

public interface TaskFlowService{
	
	TaskFlowResponseDto createTask(TaskFlowRequestDto dto);
}