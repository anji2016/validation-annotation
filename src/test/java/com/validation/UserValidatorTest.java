package com.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.validation.repository.UserRepository;
import com.validation.validator.UserValidator;

public class UserValidatorTest {
	@Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testValidatorIsAutowired() {
        assertThat(userValidator).isNotNull();
        assertThat(userRepository).isNotNull();
    }

}
