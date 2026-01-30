package com.example.demo.model;

import com.example.demo.util.PasswordUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(unique = true)
    private String username;

    @Getter(AccessLevel.PACKAGE)
    @Column(nullable = false)
    private String passwordHash;

    @Setter
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public User(String username, String rawPassword, Role role) {
        this.username = username;
        this.passwordHash = PasswordUtil.hash(rawPassword);
        this.role = role;
    }

    public void changePassword(String rawPassword) {
        this.passwordHash = PasswordUtil.hash(rawPassword);
    }

    public boolean matchesPassword(String rawPassword) {
        return PasswordUtil.matches(rawPassword, this.passwordHash);
    }

}
