package com.example.demo.config;

import com.example.demo.menu.Menu;
import com.example.demo.menu.MenuAction;
import com.example.demo.menu.MenuEntry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
public class MenuConfig {
    @Bean
    public Menu menu(ApplicationContext context) {
        // Get all MenuAction beans
        List<MenuAction> topLevelActions = context.getBeansOfType(MenuAction.class)
                .values()
                .stream()
                .filter(a -> Optional.ofNullable(a.getClass().getAnnotation(MenuEntry.class))
                        .map(entry -> Arrays.asList(entry.menus()).contains("main"))
                        .orElse(false))
                .collect(Collectors.toList());

        return new Menu(topLevelActions);
    }
}
