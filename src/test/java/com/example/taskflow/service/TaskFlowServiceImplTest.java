package com.example.taskflow.service;

import com.example.taskflow.dto.TaskFlowRequestDto;
import com.example.taskflow.model.TaskFlowEntity;
import com.example.taskflow.repository.TaskFlowRepository;
import com.example.taskflow.serviceImpl.TaskFlowServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskFlowServiceImplTest{
	
	@Mock
	private TaskFlowRepository taskFlowRepository;
	
	@InjectMocks
	private TaskFlowServiceImpl taskFlowService;
	
	@Test	
	void createTask_shouldSaveTask() {
	   //arrange
		TaskFlowRequestDto dto = new TaskFlowRequestDto();
		dto.setTitle("Learn Spring Boot");
		dto.setDescription("Practice unit testing");
	//	dto.setStatus("TODO");
		
		when(taskFlowRepository.save(any(TaskFlowEntity.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));
		
		//act
		taskFlowService.createTask(dto);
		
		
		//assert
		ArgumentCaptor<TaskFlowEntity> captor =
		        ArgumentCaptor.forClass(TaskFlowEntity.class);

		verify(taskFlowRepository).save(captor.capture());

		TaskFlowEntity savedTask = captor.getValue();

		assertEquals("Learn Spring Boot", savedTask.getTitle());
		assertEquals("Practice unit testing", savedTask.getDescription());
		assertEquals("TODO", savedTask.getStatus());
		
	}
	
	

}