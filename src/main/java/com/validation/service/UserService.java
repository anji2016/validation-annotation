package com.validation.service;

import org.springframework.stereotype.Service;

import com.validation.dto.UserDTO;

@Service
public class UserService {
	public String registerUser(UserDTO userDTO) {
		return "User registered successfully: " + userDTO.getEmail();
	}
}
