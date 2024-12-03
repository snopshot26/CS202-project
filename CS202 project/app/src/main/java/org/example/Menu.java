package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private final UserFactory userFactory;
    private final BufferedReader reader;

    public Menu(UserFactory userFactory) {
        this.userFactory = userFactory;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void showMainMenu() {
        boolean running = true;

        while (running) {
            try {
                displayMenuOptions();

                // Чтение строки с вводом пользователя
                String input = reader.readLine().trim();

                if (input == null || input.isEmpty()) {
                    System.out.println("No input received. Please enter a valid number.");
                    Thread.sleep(500); // Явная пауза перед следующим циклом
                    continue;
                }

                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    Thread.sleep(500); // Явная пауза перед следующим циклом
                    continue;
                }

                running = handleUserChoice(choice);

            } catch (IOException e) {
                System.err.println("An unexpected error occurred while reading input: " + e.getMessage());
                e.printStackTrace(); // Логирование стека для отладки.
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace(); // Логирование стека для отладки.
            }
        }
    }

    private void displayMenuOptions() {
        System.out.println("Hotel Management System Menu:");
        System.out.println("1. Guest");
        System.out.println("2. Administrator");
        System.out.println("3. Receptionist");
        System.out.println("4. Housekeeper");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private boolean handleUserChoice(int choice) {
        User user;

        switch (choice) {
            case 1:
                user = userFactory.getUser("Guest");
                if (validateUser(user)) {
                    user.handleActions();
                }
                break;
            case 2:
                user = userFactory.getUser("Administrator");
                if (validateUser(user)) {
                    user.handleActions();
                }
                break;
            case 3:
                user = userFactory.getUser("Receptionist");
                if (validateUser(user)) {
                    user.handleActions();
                }
                break;
            case 4:
                user = userFactory.getUser("Housekeeper");
                if (validateUser(user)) {
                    user.handleActions();
                }
                break;
            case 5:
                System.out.println("Exiting the system. Goodbye!");
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private boolean validateUser(User user) {
        if (user == null) {
            System.out.println("User type is invalid or not found. Please try again.");
            return false;
        }
        return true;
    }
}
