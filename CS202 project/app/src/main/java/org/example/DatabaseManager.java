package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_management";
    private static final String USER = "root";
    private static final String PASSWORD = "Niko132435.";

    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed successfully.");
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection!");
                e.printStackTrace();
            }
        }
    }

    public void createTables() {
        try (Statement statement = connection.createStatement()) {
            String createGuestsTable = "CREATE TABLE IF NOT EXISTS guests (" +
                    "guest_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "guest_name VARCHAR(100) NOT NULL" +
                    ")";
            statement.execute(createGuestsTable);

            String createRoomTypesTable = "CREATE TABLE IF NOT EXISTS room_types (" +
                    "room_type_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "type_name VARCHAR(50) NOT NULL" +
                    ")";
            statement.execute(createRoomTypesTable);

            String createRoomStatusesTable = "CREATE TABLE IF NOT EXISTS room_statuses (" +
                    "status_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "status_name VARCHAR(20) NOT NULL" +
                    ")";
            statement.execute(createRoomStatusesTable);

            String createRoomsTable = "CREATE TABLE IF NOT EXISTS rooms (" +
                    "room_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "room_type_id INT," +
                    "price DECIMAL(10, 2)," +
                    "status_id INT," +
                    "FOREIGN KEY (room_type_id) REFERENCES room_types(room_type_id)," +
                    "FOREIGN KEY (status_id) REFERENCES room_statuses(status_id)" +
                    ")";
            statement.execute(createRoomsTable);

            String createBookingsTable = "CREATE TABLE IF NOT EXISTS bookings (" +
                    "booking_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "guest_id INT," +
                    "room_id INT," +
                    "check_in_date DATE," +
                    "check_out_date DATE," +
                    "FOREIGN KEY (guest_id) REFERENCES guests(guest_id)," +
                    "FOREIGN KEY (room_id) REFERENCES rooms(room_id)" +
                    ")";
            statement.execute(createBookingsTable);

            String createEmployeesTable = "CREATE TABLE IF NOT EXISTS employees (" +
                    "employee_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "employee_name VARCHAR(100) NOT NULL," +
                    "role VARCHAR(50)" +
                    ")";
            statement.execute(createEmployeesTable);

            String createHousekeepingTasksTable = "CREATE TABLE IF NOT EXISTS housekeeping_tasks (" +
                    "task_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "room_id INT," +
                    "task_description VARCHAR(255)," +
                    "status VARCHAR(20) DEFAULT 'pending'," +
                    "assigned_to INT," +
                    "FOREIGN KEY (room_id) REFERENCES rooms(room_id)," +
                    "FOREIGN KEY (assigned_to) REFERENCES employees(employee_id)" +
                    ")";
            statement.execute(createHousekeepingTasksTable);

            String createPaymentsTable = "CREATE TABLE IF NOT EXISTS payments (" +
                    "payment_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "booking_id INT," +
                    "amount DECIMAL(10, 2)," +
                    "payment_date DATE," +
                    "FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)" +
                    ")";
            statement.execute(createPaymentsTable);

            System.out.println("All necessary tables created successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to create tables!");
            e.printStackTrace();
        }
    }
}
