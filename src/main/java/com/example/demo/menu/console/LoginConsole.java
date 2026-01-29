package com.example.demo.menu.console;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class LoginConsole {

    private final AuthService authService;
    private final Scanner scanner = new Scanner(System.in);

    public LoginConsole(AuthService authService) {
        this.authService = authService;
    }

    public User login() {
        while (true) {
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            Optional<User> user = authService.login(username, password);

            if (user.isPresent()) {
                System.out.println("Login successful!\n");
                return user.get();
            }

            System.out.println("Invalid credentials. Try again.\n");
        }
    }
}

