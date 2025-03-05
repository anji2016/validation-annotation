package com.validation.validator;

import java.util.List;

import com.validation.annotation.ValidUser;
import com.validation.dto.UserDTO;
import com.validation.enums.Type;

public class UserValidator extends BaseValidator<UserDTO, ValidUser> {

	@Override
	protected List<ValidationRule> getValidationRules(UserDTO user) {
		return List.of(
				new ValidationRule<>(MethodNameUtil.getFieldNameFromGetter(UserDTO::getName), user.getName(), "Name must contain only alphabets and spaces",
						this::isValidName, Type.WARN),
				new ValidationRule<>(MethodNameUtil.getFieldNameFromGetter(UserDTO::getName), user.getName(), "Name must not contain priya word", this::isErrName,
						Type.ERROR),
				new ValidationRule<>(MethodNameUtil.getFieldNameFromGetter(UserDTO::getEmail), user.getEmail(), "Invalid email format", this::isValidEmail, Type.ERROR),
				new ValidationRule<>(MethodNameUtil.getFieldNameFromGetter(UserDTO::getEmail), user.getEmail(), "It is an Error Email", this::isErrEmail, Type.WARN),
				new ValidationRule<>("password", user.getPassword(), "Password should contain pass",
						this::isErrorPassword, Type.WARN),
				new ValidationRule<>("password", user.getPassword(), "Password should contain password",
						this::isErrPassword, Type.WARN),
				new ValidationRule<>("password", user.getPassword(),
						"Password must be at least 6 characters long and contain one uppercase letter, one digit, and one special character",
						this::isValidPassword, Type.ERROR));
	}

	public boolean isValidName(String value) {
		return value != null && !value.matches("^[A-Za-z ]+$");
	}

	public boolean isErrName(String value) {
		return value != null && !value.contains("priya");
	}

	public boolean isValidEmail(String value) {
		return value != null && value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	}

	public boolean isErrEmail(String value) {
		return value != null && !value.contains("email");
	}

	public boolean isValidPassword(String value) {
		return value != null && value.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{6,}$");
	}

	public boolean isErrorPassword(String value) {
		return value != null && !value.contains("pass");
	}

	public boolean isErrPassword(String value) {
		return value != null && !value.contains("pass");
	}
}
