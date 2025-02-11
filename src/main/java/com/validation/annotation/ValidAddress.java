package com.validation.annotation;

import java.lang.annotation.Target;

import com.validation.validator.AddressValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddressValidator.class)
public @interface ValidAddress {
	String message() default "Invalid address data";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
