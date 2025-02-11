package com.validation.validator;

import com.validation.annotation.ValidAddress;
import com.validation.dto.AddressDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<ValidAddress, AddressDTO> {
	@Override
	public boolean isValid(AddressDTO address, ConstraintValidatorContext context) {
		boolean isValid = true;
		
		// Disable the default class-level message
	    context.disableDefaultConstraintViolation();

		if (address.getStreet().length() < 5) {
			context.buildConstraintViolationWithTemplate("Street must be at least 5 characters long")
					.addPropertyNode("street").addConstraintViolation();
			isValid = false;
		}

		if (!address.getCity().matches("^[A-Za-z ]+$")) {
			context.buildConstraintViolationWithTemplate("City must contain only alphabets and spaces")
					.addPropertyNode("city").addConstraintViolation();
			isValid = false;
		}

		return isValid;
	}
}
