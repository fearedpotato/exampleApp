package com.example.demo.menu;

public interface MenuAction {
    int number();       // Option number in the menu
    String label();     // Option label
    /**
     * Executes the action
     * @return true to continue the menu loop, false to exit the current menu
     */
    boolean execute();
}
