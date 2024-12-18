package org.example.factory;

import org.example.model.Administrator;
import org.example.model.Guest;
import org.example.model.User;
import org.example.model.Receptionist;

public class UserFactory {
    
    public static User createUser(String role, String name, String email, String phoneNumber) {
        User user = null;

        if ("guest".equalsIgnoreCase(role)) {
            user = new Guest();
        } else if ("administrator".equalsIgnoreCase(role)) {
            user = new Administrator();
        } else if ("receptionist".equalsIgnoreCase(role)) {
            user = new Receptionist();
        } else {
            throw new IllegalArgumentException("Unknown user role: " + role);
        }

        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);

        return user;
    }
}
