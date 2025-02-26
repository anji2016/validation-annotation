package com.validation.validator;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.validation.dto.ValidationError;
import com.validation.enums.Type;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public abstract class BaseValidator<T, A extends Annotation> implements ConstraintValidator<A, T> {

    private static final ThreadLocal<List<ValidationError>> threadLocalErrors = ThreadLocal.withInitial(ArrayList::new);

    @Override
    public boolean isValid(T object, ConstraintValidatorContext context) {
        List<ValidationRule<?>> rules = getValidationRules(object);
        Map<Map.Entry<String, Type>, ValidationError> groupedErrors = new LinkedHashMap<>();
        
        for (ValidationRule<?> rule : rules) {
            if (rule.isInvalid()) {
                Map.Entry<String, Type> key = Map.entry(rule.getField(), rule.getType());

                groupedErrors.computeIfAbsent(key, k -> new ValidationError(rule.getField(), rule.getType()))
                             .getMessages().add(rule.getMessage()); // Merge messages properly
            }
        }

        // Store errors in thread-local storage
        threadLocalErrors.get().addAll(groupedErrors.values());

        return groupedErrors.isEmpty();
    }

    protected abstract List<ValidationRule<?>> getValidationRules(T object);

    public static List<ValidationError> getValidationErrors() {
        return threadLocalErrors.get();
    }

    public static void clearErrors() {
        threadLocalErrors.remove();
    }
}

