package org.example;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.connect();

        if (databaseManager.isConnected()) {
            System.out.println("Connected to the database successfully!");
            databaseManager.createTables(); // Create the necessary tables if they don't exist
            UserFactory userFactory = new UserFactory(databaseManager.getConnection());
            Menu menu = new Menu(userFactory);
            menu.showMainMenu();
        } else {
            System.out.println("Database connection failed!");
        }

        databaseManager.closeConnection();
    }
}
