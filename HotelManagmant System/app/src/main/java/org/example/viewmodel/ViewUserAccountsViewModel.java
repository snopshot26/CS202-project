package org.example.viewmodel;

import org.example.dao.UserDAO;
import org.example.model.User;

import java.util.List;

public class ViewUserAccountsViewModel {
    private final UserDAO userDAO;

    public ViewUserAccountsViewModel() {
        this.userDAO = new UserDAO();
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
