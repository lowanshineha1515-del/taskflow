package com.example.taskflow.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleTaskNotFound(TaskNotFoundException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body(new ErrorResponse(404, ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
	    Map<String, String> fieldErrors = new HashMap<>();
	    ex.getBindingResult().getFieldErrors()
	            .forEach(e -> fieldErrors.put(e.getField(), e.getDefaultMessage()));
	    return ResponseEntity.badRequest()
	            .body(new ErrorResponse(400, "Validation failed", fieldErrors));
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleUnreadable(HttpMessageNotReadableException ex) {
	    return ResponseEntity.badRequest().body(new ErrorResponse(400,
	            "Malformed request body or invalid value (status must be TODO, IN_PROGRESS or DONE)"));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(new ErrorResponse(500, "Something went wrong"));
	}

}
