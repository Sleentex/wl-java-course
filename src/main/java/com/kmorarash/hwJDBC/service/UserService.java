package com.kmorarash.hwJDBC.service;

import com.kmorarash.hwJDBC.dto.UserRegistrationDto;
import com.kmorarash.hwJDBC.dto.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationDto userRegistrationDto);
    UserResponseDto getUserById(Long userId);
}
