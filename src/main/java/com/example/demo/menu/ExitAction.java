package com.example.demo.menu;

@MenuEntry(menus = {"main", "products", "categories"})
public class ExitAction implements MenuAction {

    @Override
    public int number() { return 9; }

    @Override
    public String label() { return "Exit"; }

    @Override
    public boolean execute() {
        System.out.println("Exiting...");
        return false;
    }
}
