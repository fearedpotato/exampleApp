package com.example.demo.menu.product.actions;

import com.example.demo.menu.MenuAction;
import com.example.demo.menu.MenuEntry;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;

@MenuEntry(menus = {"products"})
@Component
public class ListProductsAction implements MenuAction {
    private final ProductService productService;

    public ListProductsAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public int number() {
        return 1;
    }

    @Override
    public String label() {
        return "List Products";
    }

    @Override
    public boolean execute(User currentUser) {
        List<Product> list = productService.getAllProducts();

        if (!list.isEmpty()) {
            System.out.println("\nProducts:");
            list.forEach(System.out::println);
        } else {
            System.out.println("\nThere are no products listed");
        }

        return true;
    }
}
