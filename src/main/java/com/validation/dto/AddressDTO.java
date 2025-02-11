package com.validation.dto;

import com.validation.annotation.ValidAddress;

import jakarta.validation.constraints.NotBlank;

@ValidAddress
public class AddressDTO {
	@NotBlank(message = "Street is required")
	private String street;

	@NotBlank(message = "City is required")
	private String city;

	// Constructors, Getters, Setters
	public AddressDTO(String street, String city) {
		this.street = street;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

}
