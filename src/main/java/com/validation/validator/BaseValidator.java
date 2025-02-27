package com.validation.validator;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public abstract class BaseValidator<T, A extends Annotation> implements ConstraintValidator<A, T> {

    @Override
    public boolean isValid(T object, ConstraintValidatorContext context) {
        List<ValidationRule> rules = getValidationRules(object);

        List<ValidationRule<?>> failedRules = rules.stream()
            .filter(ValidationRule::isInvalid)
            .collect(Collectors.toList());

        if (failedRules.isEmpty()) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        failedRules.forEach(rule ->
            context.buildConstraintViolationWithTemplate(rule.getMessage() + "|" + rule.getType())
                   .addPropertyNode(rule.getField())
                   .addConstraintViolation()
        );

        return false;
    }

    protected abstract List<ValidationRule> getValidationRules(T object);
}