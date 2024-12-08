package com.kmorarash.hwJDBC;

import com.kmorarash.hwJDBC.dto.UserRegistrationDto;
import com.kmorarash.hwJDBC.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    private UserValidator userValidator;

    @BeforeEach
    void setUp() {
        userValidator = new UserValidator();
    }

    @Test
    void shouldPassValidationForValidInput() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail("validuser@example.com");
        dto.setPhoneNumber("+1234567890");
        dto.setPassword("password");
        dto.setRepeatPassword("password");

        assertDoesNotThrow(() -> userValidator.validate(dto));
    }

    @Test
    void shouldFailValidationForInvalidEmail() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail("invalid-email");
        dto.setPhoneNumber("+1234567890");
        dto.setPassword("password");
        dto.setRepeatPassword("password");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userValidator.validate(dto));
        assertEquals("Invalid email format.", exception.getMessage());
    }

    @Test
    void shouldFailValidationWhenPasswordsDoNotMatch() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail("user@example.com");
        dto.setPhoneNumber("+1234567890");
        dto.setPassword("password");
        dto.setRepeatPassword("differentpassword");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userValidator.validate(dto));
        assertEquals("Passwords do not match.", exception.getMessage());
    }

    @Test
    void shouldFailValidationForMissingRequiredFields() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail(null);
        dto.setPhoneNumber("+1234567890");
        dto.setPassword("password");
        dto.setRepeatPassword("password");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userValidator.validate(dto));
        assertEquals("Email is required.", exception.getMessage());
    }
}
