package com.example.demo.menu;

import com.example.demo.model.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

    private final Map<Integer, MenuAction> actionMap;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(List<? extends MenuAction> actions) {
        this.actionMap = actions.stream()
                .collect(Collectors.toMap(MenuAction::number, a -> a));
    }

    public void start(User currentUser) {
        boolean running = true;
        while (running) {
            show();
            int choice = readChoice();
            MenuAction action = actionMap.get(choice);
            if (action != null) {
                try {
                    running = action.execute(currentUser);
                } catch (Exception e) {
                    System.out.println("Error executing action: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void show() {
        System.out.println("\n=== Menu ===");
        actionMap.values().stream()
                .sorted(Comparator.comparingInt(MenuAction::number))
                .forEach(a -> System.out.println(a.number() + ". " + a.label()));
    }

    private int readChoice() {
        System.out.print("Enter choice: ");
        String line = scanner.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

}
