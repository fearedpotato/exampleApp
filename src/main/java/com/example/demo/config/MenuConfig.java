package com.example.demo.config;

import com.example.demo.menu.Menu;
import com.example.demo.menu.MenuAction;
import com.example.demo.menu.MenuEntry;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.util.MenuAccess;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuConfig {

    private final ApplicationContext context;

    public MenuConfig(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Create a Menu dynamically for the given user and menu type
     * @param currentUser currently logged-in user
     * @param menuType menu type, e.g., "main", "products", "categories"
     * @return Menu instance with filtered actions
     */
    public Menu createMenu(User currentUser, String menuType) {
        Role currentRole = currentUser.getRole();

        List<MenuAction> actions = context.getBeansOfType(MenuAction.class)
                .values()
                .stream()
                .filter(a -> {
                    MenuEntry entry = a.getClass().getAnnotation(MenuEntry.class);
                    if (entry == null) return false;

                    // check menu type
                    boolean menuMatch = Arrays.asList(entry.menus()).contains(menuType);

                    // check role access
                    boolean roleMatch = MenuAccess.canAccess(entry, currentRole);

                    return menuMatch && roleMatch;
                })
                .collect(Collectors.toList());

        return new Menu(actions);
    }
}
