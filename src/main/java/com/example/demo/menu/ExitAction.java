package com.example.demo.menu;

import com.example.demo.model.User;

@MenuEntry(menus = {"main", "products", "categories"})
public class ExitAction implements MenuAction {

    @Override
    public int number() { return 9; }

    @Override
    public String label() { return "Exit"; }

    @Override
    public boolean execute(User currentUser) {
        return false;
    }
}
