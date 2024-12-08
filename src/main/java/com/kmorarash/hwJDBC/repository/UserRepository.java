package com.kmorarash.hwJDBC.repository;

import com.kmorarash.hwJDBC.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    boolean existsByEmail(String email);
}