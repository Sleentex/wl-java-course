package com.kmorarash.hwHibernate;

import com.kmorarash.hwHibernate.model.Role;
import com.kmorarash.hwHibernate.model.User;
import com.kmorarash.hwHibernate.repository.RoleRepository;
import com.kmorarash.hwHibernate.repository.UserRepository;
import com.kmorarash.hwHibernate.repository.impl.RoleRepositoryImpl;
import com.kmorarash.hwHibernate.repository.impl.UserRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        RoleRepository roleRepository = new RoleRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();

        // Create Roles
        Role clientRole = new Role();
        clientRole.setName("Client");
        roleRepository.create(clientRole);

        Role superAdminRole = new Role();
        superAdminRole.setName("Super Admin");
        roleRepository.create(superAdminRole);

        // Create User
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.getRoles().add(clientRole);
        user.getRoles().add(superAdminRole);
        userRepository.create(user);

        // Retrieve User
        User retrievedUser = userRepository.findById(user.getId()).orElseThrow();
        System.out.println("Retrieved User: " + retrievedUser.getName());

        // Update User
        retrievedUser.setName("John Smith");
        userRepository.update(retrievedUser);
        System.out.println("Updated User: " + userRepository.findById(user.getId()).orElseThrow().getName());
    }
}
