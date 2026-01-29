package com.example.demo.repository;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryUserRepository extends UserRepository {

    private final List<User> users = List.of(
            new User("admin", "admin123", Role.ROLE_ADMIN),
            new User("user", "user123", Role.ROLE_USER)
    );

}
