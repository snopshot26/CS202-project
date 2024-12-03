package org.example;

import static java.lang.System.out;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Receptionist extends User {
    private final Scanner scanner;

    public Receptionist(Connection connection)
    {
        super(connection);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void handleActions() {
        boolean running = true;

        while (running) {
            try {
                System.out.println("Receptionist Menu:");
                System.out.println("1. Add New Booking");
                System.out.println("2. Modify Booking");
                System.out.println("3. Delete Booking");
                System.out.println("4. View Bookings");
                System.out.println("5. Process Payment");
                System.out.println("6. Assign Housekeeping Task");
                System.out.println("7. View All Housekeepers Records");
                System.out.println("8. Back to Main Menu");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> addNewBooking();
                    case 2 -> modifyBooking();
                    case 3 -> deleteBooking();
                    case 4 -> viewBookings();
                    case 5 -> processPayment();
                    case 6 -> assignHousekeepingTask();
                    case 7 -> viewAllHousekeepersRecords();
                    case 8 -> running = false;
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

    private void addNewBooking() {
        System.out.println("Adding new booking...");
        // Sample implementation
    }

    private void modifyBooking() {
        System.out.println("Modifying booking...");
        // Sample implementation
    }

    private void deleteBooking() {
        System.out.println("Deleting booking...");
        // Sample implementation
    }

    private void viewBookings() {
        System.out.println("Viewing bookings...");
        // Sample implementation
    }

    private void processPayment() {
        System.out.println("Processing payment...");
        // Sample implementation
    }

    private void assignHousekeepingTask() {
        System.out.println("Assigning housekeeping task...");
        // Sample implementation
    }

    private void viewAllHousekeepersRecords() {
        out.println("Viewing all housekeepers' records...");
        // Sample implementation
    }

}
