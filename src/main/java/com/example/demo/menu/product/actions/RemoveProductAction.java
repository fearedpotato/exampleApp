package com.example.demo.menu.product.actions;

import com.example.demo.menu.MenuAction;
import com.example.demo.menu.MenuEntry;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@MenuEntry(menus = {"products"})
@Component
public class RemoveProductAction implements MenuAction {

    private final ProductService productService;
    private final Scanner scanner = new Scanner(System.in);

    public RemoveProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public int number() {
        return 3;
    }

    @Override
    public String label() {
        return "Remove Product";
    }

    @Override
    public boolean execute() {
        productService.getAllProducts().forEach(System.out::println);

        int id = -1;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("\nType a product ID to delete:");
                id = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid ID, try again.");;
            }
        }

        productService.removeProduct(id);

        return true;
    }
}
