package org.example.viewmodel;

import org.example.dao.UserDAO;
import org.example.enums.UserType;
import org.example.model.User;

import java.util.List;

public class AddUserAccountViewModel {
    private final UserDAO userDAO;

    public AddUserAccountViewModel() {
        this.userDAO = new UserDAO();
    }

    public List<UserType> getUserTypes() {
        return List.of(UserType.values());
    }

    public boolean addUser(String name, String email, String phoneNumber, String password, UserType userType) {
        // Проверка на уникальность email
        User existingUser = userDAO.getUserByEmail(email);
        if (existingUser != null) {
            return false; // Пользователь с таким email уже существует
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setUserType(userType);

        return userDAO.addUser(user);
    }
}
