package com.validation.dto;

import java.util.List;

import com.validation.annotation.ValidUser;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@ValidUser
public class UserDTO {

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;

	@Valid
	private List<AddressDTO> addresses;

	// Constructors, Getters, Setters
	public UserDTO(String name, String email, String password, List<AddressDTO> addresses) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.addresses = addresses;
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

	public List<AddressDTO> getAddresses() {
		return addresses;
	}
}
