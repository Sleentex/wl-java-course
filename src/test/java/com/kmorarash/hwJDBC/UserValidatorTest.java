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
        // Arrange
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail("validuser@example.com");
        dto.setPhoneNumber("+1234567890");
        dto.setPassword("password");
        dto.setRepeatPassword("password");

        // Act & Assert
        assertDoesNotThrow(() -> userValidator.validate(dto));
    }

    @Test
    void shouldFailValidationForInvalidEmail() {
        // Arrange
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail("invalid-email");
        dto.setPhoneNumber("+1234567890");
        dto.setPassword("password");
        dto.setRepeatPassword("password");

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userValidator.validate(dto));
        assertEquals("Invalid email format.", exception.getMessage());
    }

    @Test
    void shouldFailValidationWhenPasswordsDoNotMatch() {
        // Arrange
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail("user@example.com");
        dto.setPhoneNumber("+1234567890");
        dto.setPassword("password");
        dto.setRepeatPassword("differentpassword");

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userValidator.validate(dto));
        assertEquals("Passwords do not match.", exception.getMessage());
    }

    @Test
    void shouldFailValidationForMissingRequiredFields() {
        // Arrange
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail(null); // Missing email
        dto.setPhoneNumber("+1234567890");
        dto.setPassword("password");
        dto.setRepeatPassword("password");

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userValidator.validate(dto));
        assertEquals("Email is required.", exception.getMessage());
    }
}
