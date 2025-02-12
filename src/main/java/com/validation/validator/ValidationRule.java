package com.validation.validator;

import java.util.function.Predicate;

public class ValidationRule {
    private final String field;
    private final String value;
    private final String message;
    private final Predicate<String> predicate;

    public ValidationRule(String field, String value, String message, Predicate<String> predicate) {
        this.field = field;
        this.value = value;
        this.message = message;
        this.predicate = predicate;
    }
    
    public boolean isInvalid() {
        return value == null || !predicate.test(value);
    }

	public String getField() {
		return field;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public Predicate<String> getPredicate() {
		return predicate;
	}
    
}
