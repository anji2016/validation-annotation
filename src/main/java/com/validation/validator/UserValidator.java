package com.validation.validator;

import com.validation.annotation.ValidUser;
import com.validation.dto.UserDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<ValidUser, UserDTO>{
	
	 @Override
	    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
	        boolean isValid = true;
	        
	        if (!user.getName().matches("^[A-Za-z ]+$")) {
	            context.buildConstraintViolationWithTemplate("Name must contain only alphabets and spaces")
	                    .addPropertyNode("name").addConstraintViolation();
	            isValid = false;
	        }
	        
	        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") ) {
	            context.buildConstraintViolationWithTemplate("Invalid email format")
	                    .addPropertyNode("email").addConstraintViolation();
	            isValid = false;
	        }
	        
	        if (!user.getPassword().matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{6,}$")) {
	            context.buildConstraintViolationWithTemplate("Password must be at least 6 characters long and contain one uppercase letter, one digit, and one special character")
	                    .addPropertyNode("password").addConstraintViolation();
	            isValid = false;
	        }
	        
	        return isValid;
	    }

}
