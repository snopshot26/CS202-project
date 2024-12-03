package org.example;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Administrator extends User {
    private final Scanner scanner;

    public Administrator(Connection connection)
    {
        super(connection);
        this.scanner = new Scanner(System.in);
    }

    public void handleActions() {
        boolean running = true;

        while (running) {
            try {
                System.out.println("Administrator Menu:");
                System.out.println("1. Add Room");
                System.out.println("2. Delete Room");
                System.out.println("3. Manage Room Status");
                System.out.println("4. Add User Account");
                System.out.println("5. View User Accounts");
                System.out.println("6. Generate Revenue Report");
                System.out.println("7. View All Booking Records");
                System.out.println("8. View All Housekeeping Records");
                System.out.println("9. Back to Main Menu");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 ->addRoom();
                    case 2 ->deleteRoom();
                    case 3 -> manageRoomStatus();
                    case 4 ->addUserAccount();
                    case 5 ->viewUserAccounts();
                    case 6 -> generateRevenueReport();
                    case 7 -> viewAllBookingRecords();
                    case 8 -> viewAllHousekeepingRecords();
                    case 9 -> running = false;
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

    private void addRoom() {
        // Implementation for adding a room
    }

    private void deleteRoom() {
        // Implementation for deleting a room
    }

    private void manageRoomStatus() {
        // Implementation for managing room status
    }

    private void addUserAccount() {
        // Implementation for adding a user account
    }

    private void viewUserAccounts() {
        // Implementation for viewing user accounts
    }

    private void generateRevenueReport() {
        // Implementation for generating revenue report
    }

    private void viewAllBookingRecords() {
        // Implementation for viewing all booking records
    }

    private void viewAllHousekeepingRecords() {
        // Implementation for viewing all housekeeping records
    }

}
