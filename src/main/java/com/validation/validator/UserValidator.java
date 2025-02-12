package com.validation.validator;

import java.util.List;

import com.validation.annotation.ValidUser;
import com.validation.dto.UserDTO;

public class UserValidator extends BaseValidator<UserDTO, ValidUser> {

	@Override
	protected List<ValidationRule> getValidationRules(UserDTO user) {
		return List.of(
				new ValidationRule("name", user.getName(), "Name must contain only alphabets and spaces",
						this::isValidName),

				new ValidationRule("email", user.getEmail(), "Invalid email format", this::isValidEmail),

				new ValidationRule("password", user.getPassword(),
						"Password must be at least 6 characters long and contain one uppercase letter, one digit, and one special character",
						this::isValidPassword));
	}

	public boolean isValidName(String value) {
		return value != null && value.matches("^[A-Za-z ]+$");
	}

	public boolean isValidEmail(String value) {
		return value != null && value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	}

	public boolean isValidPassword(String value) {
		return value != null && value.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{6,}$");
	}
}
