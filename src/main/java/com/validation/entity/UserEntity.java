package com.validation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class UserEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id; 

	@NotBlank(message = "Name is required")
	@NotNull(message = "Name cannot be null")
	private String name;

	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
