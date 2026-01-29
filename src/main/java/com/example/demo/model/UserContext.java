package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class UserContext {
    private User currentUser;

    public UserContext() {

    }

    public UserContext(User currentUser) {
        this.currentUser = currentUser;
    }
}
