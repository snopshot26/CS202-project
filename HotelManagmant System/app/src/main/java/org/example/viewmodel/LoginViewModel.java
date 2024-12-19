package org.example.viewmodel;

import org.example.model.User;
import org.example.service.UserService;

public class LoginViewModel {
    private final UserService userService;

    public LoginViewModel() {
        this.userService = new UserService();
    }

    public User authenticateUser(String email, String password) {
        User user = userService.getUserByEmailAndPassword(email, password);
        return user;
    }
}
