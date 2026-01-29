package com.example.demo.menu;

import com.example.demo.model.User;

public interface MenuAction {
    int number();       // Option number in the menu
    String label();     // Option label
    /**
     * Executes the action
     * @return true to continue the menu loop, false to exit the current menu
     */
    boolean execute(User currentUser);
}
