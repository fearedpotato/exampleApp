package com.example.demo.menu.category.actions;

import com.example.demo.menu.MenuAction;
import com.example.demo.menu.MenuEntry;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@MenuEntry(menus = {"categories"})
@Component
public class RemoveCategoryMenuAction implements MenuAction {
    private final CategoryService categoryService;
    private final Scanner scanner = new Scanner(System.in);

    public RemoveCategoryMenuAction(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public int number() {
        return 3;
    }

    @Override
    public String label() {
        return "Remove Category";
    }

    @Override
    public boolean execute() {
        categoryService.getAllCategories().forEach(System.out::println);

        int id = -1;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("\nType a category ID to delete:");
                id = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid ID, try again.");;
            }
        }

        categoryService.removeCategory(id);

        return true;
    }
}
