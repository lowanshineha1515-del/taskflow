package com.example.taskflow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskflow.dto.RegisterRequestDto;
import com.example.taskflow.dto.RegisterResponseDto;
import com.example.taskflow.dto.TaskFlowRequestDto;
import com.example.taskflow.dto.TaskFlowResponseDto;
import com.example.taskflow.service.TaskFlowService;
import com.example.taskflow.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
class TaskFlowController {

	private final TaskFlowService taskFlowService;
	private final UserService userService;

	  public TaskFlowController(
	            TaskFlowService taskFlowService,
	            UserService userService) {

	        this.taskFlowService = taskFlowService;
	        this.userService = userService;
	    }
	  
	@PostMapping
	public ResponseEntity<TaskFlowResponseDto> createTask(@Valid @RequestBody TaskFlowRequestDto dto) {
		TaskFlowResponseDto response =  taskFlowService.createTask(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<List<TaskFlowResponseDto>> getAllTasks() {
		List<TaskFlowResponseDto> response = taskFlowService.getAllTasks();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskFlowResponseDto> getTaskById(@PathVariable long id) {
		TaskFlowResponseDto response = taskFlowService.getTaskById(id);
		return ResponseEntity.ok(response);
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String,String>>updateTask(@PathVariable long id, @Valid @RequestBody TaskFlowRequestDto dto) {
		taskFlowService.updateTask(id, dto);
		Map<String, String> response = new HashMap<>();
		response.put("message","Task updated successfully");
		return ResponseEntity.ok(response);
		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>>deleteTask(@PathVariable  long id) {
		taskFlowService.deleteTask(id);
		Map<String, String> response= new HashMap<>();
		response.put("message", "Task deleted sussessfully");
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDto>register(@RequestBody RegisterRequestDto dto){
		RegisterResponseDto responseDto = userService.registerUser(dto);
		return ResponseEntity.ok(responseDto);
		}
	
	@GetMapping("/token")
	public CsrfToken getToken(CsrfToken csrfToken) {
		return csrfToken;
	}

}
 @GetMapping("/login")
 public String login() {
	 return "print login page";
 }
