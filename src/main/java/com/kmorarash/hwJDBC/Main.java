package com.kmorarash.hwJDBC;

import com.kmorarash.hwJDBC.dto.UserRegistrationDto;
import com.kmorarash.hwJDBC.dto.UserResponseDto;
import com.kmorarash.hwJDBC.repository.UserRepositoryJdbcImpl;
import com.kmorarash.hwJDBC.service.UserService;
import com.kmorarash.hwJDBC.service.UserServiceImpl;
import com.kmorarash.hwJDBC.validator.UserValidator;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        // Load environment variables from .env
        Dotenv dotenv = Dotenv.load();

        // Get database configurations
        String jdbcUrl = dotenv.get("DB_URL");
        String jdbcUsername = dotenv.get("DB_USERNAME");
        String jdbcPassword = dotenv.get("DB_PASSWORD");

        // Initialize repository, validator, and service
        UserRepositoryJdbcImpl userRepository = new UserRepositoryJdbcImpl(jdbcUrl, jdbcUsername, jdbcPassword);
        UserValidator userValidator = new UserValidator();
        UserService userService = new UserServiceImpl(userRepository, userValidator);

        // Register a user
        UserRegistrationDto registrationDto = new UserRegistrationDto();
        registrationDto.setEmail("test123@example.com");
        registrationDto.setPhoneNumber("+1234567890");
        registrationDto.setPassword("password");
        registrationDto.setRepeatPassword("password");

        System.out.println("Registering user...");
        UserResponseDto userResponse = userService.registerUser(registrationDto);
        System.out.println("User registered: " + userResponse);

        // Retrieve user by ID
        System.out.println("Fetching user by ID...");
        UserResponseDto fetchedUser = userService.getUserById(userResponse.id());
        System.out.println("Fetched User: " + fetchedUser);
    }
}
