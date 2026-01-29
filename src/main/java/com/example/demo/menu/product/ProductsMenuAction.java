package com.example.demo.menu.product;

import com.example.demo.menu.Menu;
import com.example.demo.menu.MenuAction;
import com.example.demo.menu.MenuEntry;
import com.example.demo.model.User;
import com.example.demo.util.MenuAccess;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@MenuEntry(menus = {"main"})
public class ProductsMenuAction implements MenuAction {

    private final ApplicationContext context;

    public ProductsMenuAction(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public int number() { return 1; }

    @Override
    public String label() { return "Products Menu"; }

    @Override
    public boolean execute(User currentUser) {
        List<MenuAction> subActions = context.getBeansOfType(MenuAction.class)
                .values()
                .stream()
                .filter(a -> {
                    MenuEntry entry = a.getClass().getAnnotation(MenuEntry.class);
                    if (entry == null) return false;

                    boolean menuMatch = Arrays.asList(entry.menus()).contains("products");
                    boolean roleMatch = MenuAccess.canAccess(entry, currentUser.getRole());

                    return menuMatch && roleMatch;
                })
                .collect(Collectors.toList());

        Menu productsMenu = new Menu(subActions);
        productsMenu.start(currentUser);
        return true;
    }
}
