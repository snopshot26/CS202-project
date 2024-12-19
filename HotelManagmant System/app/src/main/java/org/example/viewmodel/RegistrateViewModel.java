package org.example.viewmodel;

import org.example.enums.UserType;
import org.example.model.User;
import org.example.service.UserService;

public class RegistrateViewModel {
    private final UserService userService;

    public RegistrateViewModel() {
        this.userService = new UserService();
    }

    public void registrateUser(String name , String email, String password, UserType userType,  String phoneNumber) {
        User user = new User(name, email,phoneNumber, userType, password);
        userService.addUser(user);
    }
}
