package com.example.taskflow.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.taskflow.dto.TaskFlowRequestDto;
import com.example.taskflow.dto.TaskFlowResponseDto;
import com.example.taskflow.exception.TaskNotFoundException;
import com.example.taskflow.model.TaskFlowEntity;
import com.example.taskflow.repository.TaskFlowRepository;
import com.example.taskflow.service.TaskFlowService;

@Service
public class TaskFlowServiceImpl implements TaskFlowService {

	private final TaskFlowRepository taskFlowRepository;

	public TaskFlowServiceImpl(TaskFlowRepository taskFlowRepository) {
		this.taskFlowRepository = taskFlowRepository;
	}

	@Override
	public TaskFlowResponseDto createTask(TaskFlowRequestDto dto) {
		TaskFlowEntity entity = new TaskFlowEntity();
		entity.setDescription(dto.getDescription());
		entity.setStatus(dto.getStatus());
		entity.setTitle(dto.getTitle());
		TaskFlowEntity savedEntitiy = taskFlowRepository.save(entity);
		
		TaskFlowResponseDto response = new TaskFlowResponseDto();
		response.setId(savedEntitiy.getId());
		response.setTitle(savedEntitiy.getTitle());
		response.setDescription(savedEntitiy.getDescription());
		response.setStatus(savedEntitiy.getStatus());
		
		return response;

	}

	@Override
	public List<TaskFlowResponseDto> getAllTasks() {
		List<TaskFlowEntity> entities = taskFlowRepository.findAll();
		List<TaskFlowResponseDto> responseList = new ArrayList<>();
		for (TaskFlowEntity entity : entities) {
			TaskFlowResponseDto response = new TaskFlowResponseDto();
			response.setId(entity.getId());
			response.setDescription(entity.getDescription());
			response.setStatus(entity.getStatus());
			response.setTitle(entity.getTitle());
			responseList.add(response);
		}
		return responseList;
	}

	@Override
	public TaskFlowResponseDto getTaskById(long id) {
		Optional<TaskFlowEntity> optional = taskFlowRepository.findById(id);
		if (optional.isPresent()) {
			TaskFlowResponseDto response = new TaskFlowResponseDto();
			response.setId(optional.get().getId());
			response.setDescription(optional.get().getDescription());
			response.setStatus(optional.get().getStatus());
			response.setTitle(optional.get().getTitle());
			return response;
		}
		throw new TaskNotFoundException("task not found for id " + id);
	}

	@Override
	public void updateTask(long id, TaskFlowRequestDto dto) {
		Optional<TaskFlowEntity> optional = taskFlowRepository.findById(id);
		if (optional.isPresent()) {
			TaskFlowEntity entity = optional.get();
			entity.setDescription(dto.getDescription());
			entity.setStatus(dto.getStatus());
			entity.setTitle(dto.getTitle());
			taskFlowRepository.save(entity);
		} else {
			throw new TaskNotFoundException("Task could not be updated for id " + id);
		}
	}

	@Override
	public void deleteTask(long id) {
		Optional<TaskFlowEntity> optional = taskFlowRepository.findById(id);
		if (optional.isPresent()) {
			taskFlowRepository.delete(optional.get());
		} else {
			throw new TaskNotFoundException("Task could not be deleted for id " + id);
		}
	}

}
