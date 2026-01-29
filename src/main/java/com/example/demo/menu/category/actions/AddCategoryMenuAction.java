package com.example.demo.menu.category.actions;

import com.example.demo.menu.MenuAction;
import com.example.demo.menu.MenuEntry;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@MenuEntry(menus = {"categories"}, roles = {Role.ROLE_ADMIN})
@Component
public class AddCategoryMenuAction implements MenuAction {
    private final CategoryService categoryService;
    private final Scanner scanner = new Scanner(System.in);

    public AddCategoryMenuAction(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public int number() {
        return 1;
    }

    @Override
    public String label() {
        return "Add Category";
    }

    @Override
    public boolean execute(User currentUser) {
        if (currentUser.getRole() != Role.ROLE_ADMIN) {
            System.out.println("Access Denied");
            return true;
        }

        System.out.println("\n=== Add Category ===");

        System.out.println("\nType category name");
        String name = scanner.nextLine();

        System.out.println("\nType category description");
        String description = scanner.nextLine();

        categoryService.addCategory(name, description);

        return true;
    }
}
