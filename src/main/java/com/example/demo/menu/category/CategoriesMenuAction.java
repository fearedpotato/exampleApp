package com.example.demo.menu.category;

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
public class CategoriesMenuAction implements MenuAction {

    private final Menu categoriesMenu;

    public CategoriesMenuAction(ApplicationContext context) {
        List<MenuAction> subActions = context.getBeansOfType(MenuAction.class)
                .values()
                .stream()
                .filter(a -> Optional.ofNullable(a.getClass().getAnnotation(MenuEntry.class))
                        .map(e -> Arrays.asList(e.menus()).contains("categories"))
                        .orElse(false))
                .collect(Collectors.toList());

        this.categoriesMenu = new Menu(subActions);
    }

    @Override
    public int number() { return 2; }

    @Override
    public String label() { return "Categories Menu"; }

    @Override
    public boolean execute() {
        categoriesMenu.start();
        return true;
    }
}
