package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public User getUserByEmailAndPassword(String email, String password) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.createQuery(
                "FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return user;
    }
    
    
}
