package org.example.service;

import java.util.List;

import org.example.dao.UserDAO;
import org.example.factory.UserFactory;
import org.example.model.Guest;
import org.example.model.User;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public User registerUser(String role, String name, String email, String phoneNumber) {
        return UserFactory.createUser(role, name, email, phoneNumber);
    }

    public User getUser(long userId){
        return this.userDAO.getUserById(userId);
    }

    public List<User> getAllUsers(){
        return this.userDAO.getAllUsers();
    }
    
    public User getUserByEmailAndPassword(String password , String email){
        return this.userDAO.getUserByEmailAndPassword(password, email);
    }

    public boolean addUser(User user) {
        return this.userDAO.addUser(user);
    }

    public void addGuest(Guest guest) {
        userDAO.addGuest(guest);
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }


}
