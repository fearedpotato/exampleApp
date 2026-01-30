package com.example.demo.menu;

import com.example.demo.model.User;
import com.example.demo.model.UserContext;

@MenuEntry(menus = {"main"})
public class LogoutAction implements MenuAction {

    private final UserContext userContext;

    public LogoutAction(UserContext userContext) {
        this.userContext = userContext;
    }

    @Override
    public int number() {
        return 10;
    }

    @Override
    public String label() {
        return "Logout";
    }

    @Override
    public boolean execute(User currentUser) {
        System.out.println("\nLogging out...\n");
        userContext.setCurrentUser(null);
        return false;
    }
}
