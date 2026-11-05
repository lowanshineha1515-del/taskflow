package com.example.taskflow.service;

import com.example.taskflow.dto.RegisterRequestDto;
import com.example.taskflow.dto.RegisterResponseDto;

public interface UserService {
	RegisterResponseDto registerUser(RegisterRequestDto dto);
}
