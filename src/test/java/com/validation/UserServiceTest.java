package com.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.validation.dto.UserDTO;
import com.validation.repository.UserRepository;
import com.validation.service.UserService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService; // Assuming this is your service class

    private UserDTO validUserDTO;
    private UserDTO invalidUserDTO;
    
    @Autowired
    private Validator validator;

    @BeforeEach
    void setup() {
        validUserDTO = new UserDTO("John Doe", "johndoe@example.com", "Passw@1");
        invalidUserDTO = new UserDTO("John Doe", "", "Passw@1"); // Missing email
    }

    @Test
    void testRegisterUser_ValidInput() {
        // When
        String result = userService.registerUser(validUserDTO);
        // Then
        assertNotNull(result);
    }

    @Test
    void testRegisterUser_InvalidInput() {
        // When & Then
        Exception exception = assertThrows(ValidationException.class, () -> {
            userService.registerUser(invalidUserDTO);
        });

        assertEquals("Email is required", exception.getMessage()); // Assuming this is thrown in your service
    }
    
    @Test
    void testRegisterUser_InvalidInput_ShouldFailValidation() {
        // Given - Invalid UserDTO
        UserDTO userDTO = new UserDTO("", "invalid-email", "123"); // Invalid name, email, and password

        // When - Manually trigger validation
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then - Assert that violations exist
        assertFalse(violations.isEmpty());
        violations.forEach(v -> System.out.println(v.getPropertyPath() + " -> " + v.getMessage()));
    }
}
