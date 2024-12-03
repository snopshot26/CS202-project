package org.example;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Guest extends User {
    private final Scanner scanner;

    public Guest (Connection connection){
        super(connection);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void handleActions() {
        boolean running = true;

        while (running) {
            try {
                System.out.println("Guest Menu:");
                System.out.println("1. Add New Booking");
                System.out.println("2. View Available Rooms");
                System.out.println("3. View My Bookings");
                System.out.println("4. Cancel Booking");
                System.out.println("5. Back to Main Menu");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> addNewBooking();
                    case 2 -> viewAvailableRooms();
                    case 3 -> viewMyBookings();
                    case 4 -> cancelBooking();
                    case 5 -> running = false;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void addNewBooking() {
        // Implementation for adding a new booking
    }

    private void viewAvailableRooms() {
        // Implementation for viewing available rooms
    }

    private void viewMyBookings() {
        // Implementation for viewing guest's bookings
    }

    private void cancelBooking() {
        // Implementation for canceling a booking
    }
}
