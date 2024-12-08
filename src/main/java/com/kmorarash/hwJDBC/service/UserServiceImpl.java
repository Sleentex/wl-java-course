package com.kmorarash.hwJDBC.service;


import com.kmorarash.hwJDBC.dto.UserRegistrationDto;
import com.kmorarash.hwJDBC.dto.UserResponseDto;
import com.kmorarash.hwJDBC.model.User;
import com.kmorarash.hwJDBC.repository.UserRepository;
import com.kmorarash.hwJDBC.validator.UserValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserServiceImpl(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @Override
    public UserResponseDto registerUser(UserRegistrationDto dto) {
        userValidator.validate(dto);

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email is already registered.");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(dto.getPassword());

        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser.getId(), savedUser.getEmail(), savedUser.getPhoneNumber());
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponseDto(user.getId(), user.getEmail(), user.getPhoneNumber());
    }
}
