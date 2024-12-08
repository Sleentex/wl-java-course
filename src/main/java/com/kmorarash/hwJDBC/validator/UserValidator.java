package com.kmorarash.hwJDBC.validator;


import com.kmorarash.hwJDBC.dto.UserRegistrationDto;

import java.util.regex.Pattern;

public class UserValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?\\d{10,15}$");

    public void validate(UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto.getEmail() == null || userRegistrationDto.getEmail().isEmpty()) {
            throw new RuntimeException("Email is required.");
        }
        if (!userRegistrationDto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("Invalid email format.");
        }
        if (userRegistrationDto.getPassword() == null || userRegistrationDto.getRepeatPassword() == null) {
            throw new RuntimeException("Passwords are required.");
        }
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getRepeatPassword())) {
            throw new RuntimeException("Passwords do not match.");
        }

        if (userRegistrationDto.getPhoneNumber() != null && !PHONE_PATTERN.matcher(userRegistrationDto.getPhoneNumber()).matches()) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }
}
