package com.example.demo.repository;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}*/

@Repository
public class UserRepository {

    private final List<User> users = List.of(
            new User("admin", "admin123", Role.ROLE_ADMIN),
            new User("user", "user123", Role.ROLE_USER)
    );

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }
}
