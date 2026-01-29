package com.example.demo.menu;

import com.example.demo.model.Role;
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
     * "products"       ->  products menu
     * "categories"     ->  categories menu
     */
    String[] menus() default {};

    /**
     * ROLE_USER        -> User menu
     * ROLE_ADMIN       -> Admin menu
     * Defaulted to ROLE_USER to avoid accidental admin-only menus access.
     */
    Role[] roles() default { Role.ROLE_USER };
}
