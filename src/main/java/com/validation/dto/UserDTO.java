package com.validation.dto;

import com.validation.annotation.ValidUser;

import jakarta.validation.constraints.NotBlank;

@ValidUser
public class UserDTO {

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;

	// Constructors, Getters, Setters
	public UserDTO(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
