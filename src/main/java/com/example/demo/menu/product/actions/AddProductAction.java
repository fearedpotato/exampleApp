package com.example.demo.menu.product.actions;

import com.example.demo.menu.MenuAction;
import com.example.demo.menu.MenuEntry;
import com.example.demo.model.Category;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@MenuEntry(menus = {"products"}, roles = {Role.ROLE_ADMIN})
@Component
public class AddProductAction implements MenuAction{
    private final ProductService productService;
    private final CategoryService categoryService;
    private final Scanner scanner = new Scanner(System.in);

    public AddProductAction(ProductService productService, CategoryService categoryService)   {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public int number() {
        return 1;
    }

    @Override
    public String label() {
        return "Add Product";
    }

    @Override
    public boolean execute(User currentUser) {
        if (currentUser.getRole() != Role.ROLE_ADMIN) {
            System.out.println("Access Denied");
            return true;
        }

        System.out.println("\n=== Add Product ===");

        System.out.println("Type the product's name: ");
        String name = scanner.nextLine();

        System.out.println("Type product description: ");
        String description = scanner.nextLine();

        double price = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("Type product price: ");
                price = Double.parseDouble(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid price, try again.");
            }
        }

        categoryService.getAllCategories().forEach(System.out::println);

        Category category = null;

        int id = -1;
        while (valid) {
            try {
                System.out.println("Which category does this product belong to?");
                id = Integer.parseInt(scanner.nextLine());
                category = categoryService.getCategoryById(id);
                valid = false;

            } catch (Exception e) {
                System.out.println("Invalid ID, try again.");;
            }
        }

        if (category != null) {
            productService.addProduct(name, description, price, category);
            System.out.println("Product added successfully!");
        }

        return true;
    }
}
