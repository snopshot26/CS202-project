package org.example;

import java.sql.Connection;

public class UserFactory {
    private final Connection connection;

    public UserFactory(Connection connection) {
        this.connection = connection;
    }

    public User getUser(String userType) {
        switch (userType) {
            case "Guest":
                return new Guest(connection);
            case "Administrator":
                return new Administrator(connection);
            case "Receptionist":
                return new Receptionist(connection);
            case "Housekeeper":
                return new Housekeeper(connection);
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }
}
