package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.User;
import org.hibernate.Session;

public class UserDAO {
    public User getUserById(long userId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(User.class, userId);
        }
    }

    public List<User> getAllUsers(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM User",User.class).list();
        }
    }
}
