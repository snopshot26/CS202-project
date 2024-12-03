package org.example;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Housekeeper extends User {
    private final Scanner scanner;

    public Housekeeper(Connection connection) {
        super(connection);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void handleActions() {
        boolean running = true;

        while (running) {
            try {
                System.out.println("Housekeeper Menu:");
                System.out.println("1. View Pending Housekeeping Tasks");
                System.out.println("2. View Completed Housekeeping Tasks");
                System.out.println("3. Update Task Status to Completed");
                System.out.println("4. View My Cleaning Schedule");
                System.out.println("5. Back to Main Menu");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> viewPendingTasks();
                    case 2 -> viewCompletedTasks();
                    case 3 -> updateTaskStatus();
                    case 4 -> viewCleaningSchedule();
                    case 5 -> running = false;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void viewPendingTasks() {
        System.out.println("Viewing pending housekeeping tasks...");
        // Sample implementation
    }

    private void viewCompletedTasks() {
        System.out.println("Viewing completed housekeeping tasks...");
        // Sample implementation
    }

    private void updateTaskStatus() {
        System.out.println("Updating task status to completed...");
        // Sample implementation
    }

    private void viewCleaningSchedule() {
        System.out.println("Viewing cleaning schedule...");
        // Sample implementation
    }
}