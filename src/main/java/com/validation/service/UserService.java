package com.validation.service;

import org.springframework.stereotype.Service;

import com.validation.dto.UserDTO;

import jakarta.validation.Valid;

@Service
public class UserService {
	public String registerUser(@Valid UserDTO userDTO) {
		return "User registered successfully: " + userDTO.getEmail();
	}
}
