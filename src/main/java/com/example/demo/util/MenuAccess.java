package com.example.demo.util;

import com.example.demo.menu.MenuEntry;
import com.example.demo.model.Role;

import java.util.Arrays;

public class MenuAccess {
    public static boolean canAccess(MenuEntry entry, Role userRole) {
        if (userRole == Role.ROLE_ADMIN) return true;
        return Arrays.asList(entry.roles()).contains(userRole);
    }
}
