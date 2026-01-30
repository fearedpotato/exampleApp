package com.example.demo.menu.category.actions;

import com.example.demo.menu.MenuAction;
import com.example.demo.menu.MenuEntry;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@MenuEntry(menus = {"categories"})
@Component
public class ListCategoriesMenuAction implements MenuAction {

    private final CategoryService categoryService;

    public ListCategoriesMenuAction(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public int number() {
        return 1;
    }

    @Override
    public String label() {
        return "List Categories";
    }

    @Override
    public boolean execute(User currentUser) {
        List<Category> list = categoryService.getAllCategories();

        if (!list.isEmpty()) {
            System.out.println("\nCategories:");
            list.forEach(System.out::println);

        } else {
            System.out.println("\nThere are no categories registered");
        }

        return true;
    }
}
