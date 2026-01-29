package com.example.demo.menu.category;

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
public class CategoriesMenuAction implements MenuAction {

    private final ApplicationContext context;

    public CategoriesMenuAction(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public int number() { return 2; }

    @Override
    public String label() { return "Categories Menu"; }

    @Override
    public boolean execute(User currentUser) {
        List<MenuAction> subActions = context.getBeansOfType(MenuAction.class)
                .values()
                .stream()
                .filter(a -> {
                    MenuEntry entry = a.getClass().getAnnotation(MenuEntry.class);
                    if (entry == null) return false;

                    boolean menuMatch = Arrays.asList(entry.menus()).contains("categories");
                    boolean roleMatch = MenuAccess.canAccess(entry, currentUser.getRole());

                    return menuMatch && roleMatch;
                })
                .collect(Collectors.toList());

        Menu categoriesMenu = new Menu(subActions);
        categoriesMenu.start(currentUser); // start submenu loop
        return true; // return to main menu after submenu exits
    }
}
