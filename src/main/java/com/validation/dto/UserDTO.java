package com.validation.dto;

import com.validation.annotation.ValidUser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@ValidUser
public class UserDTO{

	@NotBlank(message = "Name is required")
	@NotNull(message = "Name cannot be null")
	private String name;

	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;

	/*
	 * @Valid private List<AddressDTO> addresses;
	 */

	/*
	 * // Constructors, Getters, Setters public UserDTO(String name, String email,
	 * String password, List<AddressDTO> addresses) { this.name = name; this.email =
	 * email; this.password = password; this.addresses = addresses; }
	 */
	
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
	

	/*
	 * public List<AddressDTO> getAddresses() { return addresses; }
	 */
	
	
	
}
