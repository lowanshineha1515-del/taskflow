package com.example.taskflow.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.taskflow.dto.RegisterRequestDto;
import com.example.taskflow.dto.RegisterResponseDto;
import com.example.taskflow.model.AppUser;
import com.example.taskflow.repository.UserRepository;
import com.example.taskflow.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	
	public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}
	
	@Override
	public RegisterResponseDto registerUser(RegisterRequestDto dto) {
		
		AppUser user = new AppUser();
		user.setName(dto.getName());
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		user.setPassword(encodedPassword);
		AppUser savedUser = userRepository.save(user);

		RegisterResponseDto responseDto = new RegisterResponseDto();
		responseDto.setId(savedUser.getId());
		responseDto.setName(savedUser.getName());

		return responseDto;

	}

}
