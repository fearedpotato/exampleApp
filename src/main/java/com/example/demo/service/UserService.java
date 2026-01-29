package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    /*public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }*/

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO
    /*public User register(String username, String password, Role role) {
        User user = new User();
        user.setUsername(username);

        user.setPassword(password);
        //user.setPassword(passwordEncoder.encode(password));

        user.setRole(role);
        return userRepository.save(user);
    }*/
}
