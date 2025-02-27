package com.validation.exception;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.validation.dto.ValidationError;
import com.validation.enums.Type;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, Map<Type, List<String>>> errorMap = new LinkedHashMap<>();

        // Extract messages and group them by field and type
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String[] parts = error.getDefaultMessage().split("\\|", 2); // Extract message and errorType
            String message = parts[0].trim();
            Type errorType = parts.length > 1 ? Type.valueOf(parts[1].trim()) : Type.ERROR; // Default to ERROR

            errorMap
                .computeIfAbsent(error.getField(), key -> new LinkedHashMap<>())
                .computeIfAbsent(errorType, key -> new ArrayList<>())
                .add(message);
        });

        // Convert to response format
        List<ValidationError> validationErrors = errorMap.entrySet()
            .stream()
            .flatMap(entry -> entry.getValue().entrySet().stream()
                .map(typeEntry -> new ValidationError(entry.getKey(), typeEntry.getValue(), typeEntry.getKey())))
            .toList();

        return ResponseEntity.badRequest().body(validationErrors);
    }
}

