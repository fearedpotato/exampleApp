package com.example.demo.menu;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface MenuEntry {
    /**
     * "main"           ->  main menu
     * "products"       ->  products menu
     * "categories"     ->  categories menu
     *
     * @return
     */
    String[] menus() default {};
}
