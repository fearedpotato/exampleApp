package com.example.demo.menu.product;

import com.example.demo.menu.Menu;
import com.example.demo.menu.MenuAction;
import com.example.demo.menu.MenuEntry;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@MenuEntry(menus = {"main"})
@Component
public class ProductsMenuAction implements MenuAction {

    private final Menu productsMenu;

    public ProductsMenuAction(ApplicationContext context) {
        List<MenuAction> subActions = context.getBeansOfType(MenuAction.class)
                .values()
                .stream()
                .filter(a -> Optional.ofNullable(a.getClass().getAnnotation(MenuEntry.class))
                        .map(e -> Arrays.asList(e.menus()).contains("products"))
                        .orElse(false))
                .collect(Collectors.toList());

        this.productsMenu = new Menu(subActions);
    }

    @Override
    public int number() { return 1; }

    @Override
    public String label() { return "Products Menu"; }

    @Override
    public boolean execute() {
        productsMenu.start();
        return true;
    }
}
