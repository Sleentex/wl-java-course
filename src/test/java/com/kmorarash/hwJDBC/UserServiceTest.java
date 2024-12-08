package com.kmorarash.hwJDBC;

import com.kmorarash.hwJDBC.dto.UserRegistrationDto;
import com.kmorarash.hwJDBC.dto.UserResponseDto;
import com.kmorarash.hwJDBC.model.User;
import com.kmorarash.hwJDBC.service.UserService;
import com.kmorarash.hwJDBC.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.kmorarash.hwJDBC.repository.UserRepository;
import com.kmorarash.hwJDBC.validator.UserValidator;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserValidator userValidator;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userValidator = mock(UserValidator.class);
        userService = new UserServiceImpl(userRepository, userValidator);
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        // Arrange
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail("newuser@example.com");
        dto.setPhoneNumber("+1234567890");
        dto.setPassword("password");
        dto.setRepeatPassword("password");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setEmail(dto.getEmail());
        savedUser.setPhoneNumber(dto.getPhoneNumber());
        savedUser.setPassword(dto.getPassword());

        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        var response = userService.registerUser(dto);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("newuser@example.com", response.email());
        assertEquals("+1234567890", response.phoneNumber());

        verify(userValidator, times(1)).validate(dto);
        verify(userRepository, times(1)).existsByEmail(dto.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        // Arrange
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail("existinguser@example.com");
        dto.setPhoneNumber("+1234567890");
        dto.setPassword("password");
        dto.setRepeatPassword("password");

        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(dto));
        assertEquals("Email is already registered.", exception.getMessage());

        verify(userValidator, times(1)).validate(dto);
        verify(userRepository, times(1)).existsByEmail(dto.getEmail());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionOnValidationFailure() {
        // Arrange
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail("invalid-email");
        dto.setPhoneNumber("+1234567890");
        dto.setPassword("password");
        dto.setRepeatPassword("password");

        doThrow(new RuntimeException("Invalid email format"))
                .when(userValidator)
                .validate(dto);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(dto));
        assertEquals("Invalid email format", exception.getMessage());

        verify(userValidator, times(1)).validate(dto);
        verify(userRepository, never()).existsByEmail(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldGetUserByIdSuccessfully() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setEmail("user@example.com");
        user.setPhoneNumber("+1234567890");
        user.setPassword("password");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        UserResponseDto response = userService.getUserById(userId);

        // Assert
        assertNotNull(response);
        assertEquals(userId, response.id());
        assertEquals("user@example.com", response.email());
        assertEquals("+1234567890", response.phoneNumber());

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        Long userId = 99L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.getUserById(userId));
        assertEquals("User not found", exception.getMessage());

        verify(userRepository, times(1)).findById(userId);
    }
}

