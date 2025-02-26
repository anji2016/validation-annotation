package com.validation.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.validation.dto.ValidationError;
import com.validation.enums.Type;
import com.validation.validator.BaseValidator;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<Map.Entry<String, Type>, ValidationError> errorMap = new HashMap<>();

        // Process @NotNull and other Jakarta validation errors
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            Map.Entry<String, Type> key = Map.entry(error.getField(), Type.ERROR); // Always ERROR type for Jakarta validations

            errorMap.computeIfAbsent(key, k -> new ValidationError(k.getKey(), k.getValue()))
                    .getMessages().add(error.getDefaultMessage());
        }

        // Fetch custom validation errors from BaseValidator
        List<ValidationError> customErrors = BaseValidator.getValidationErrors();
        for (ValidationError customError : customErrors) {
            Map.Entry<String, Type> key = Map.entry(customError.getField(), customError.getType());

            errorMap.computeIfAbsent(key, k -> new ValidationError(k.getKey(), k.getValue()))
                    .getMessages().addAll(customError.getMessages());
        }

        // Clear stored errors to prevent memory leaks
        BaseValidator.clearErrors();

        return new ResponseEntity<>(new ArrayList<>(errorMap.values()), HttpStatus.BAD_REQUEST);
    }
}

