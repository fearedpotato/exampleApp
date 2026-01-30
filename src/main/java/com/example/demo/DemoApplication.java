package com.example.demo;

import com.example.demo.config.MenuConfig;
import com.example.demo.menu.Menu;
import com.example.demo.menu.category.CategoriesMenuAction;
import com.example.demo.menu.console.LoginConsole;
import com.example.demo.menu.product.ProductsMenuAction;
import com.example.demo.model.User;
import com.example.demo.model.UserContext;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final LoginConsole loginConsole;
	private final MenuConfig menuConfig;
	private final UserContext userContext;
	private final ApplicationContext context;

	public DemoApplication(LoginConsole loginConsole, MenuConfig menuConfig, UserContext userContext, ApplicationContext applicationContext) {
		this.loginConsole = loginConsole;
        this.menuConfig = menuConfig;
		this.userContext = userContext;
		this.context = applicationContext;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String @NonNull ... args) {
		while (true) {
			User currentUser = loginConsole.login();
			userContext.setCurrentUser(currentUser);

			new CategoriesMenuAction(context);
			new ProductsMenuAction(context);

			Menu mainMenu = menuConfig.createMenu(currentUser, "main");
			mainMenu.start(currentUser);

			if (!userContext.isLogged()) continue;
			break;
		}


	}
}
