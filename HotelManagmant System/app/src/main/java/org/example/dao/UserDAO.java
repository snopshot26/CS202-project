package org.example.dao;

import org.example.config.HibernateUtil;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO {

    public User getUserById(long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, userId);
        }
    }

    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
    
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                user = session.createQuery(
                        "FROM User u WHERE u.email = :email AND u.password = :password", User.class
                )
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResult();
    
                transaction.commit();
            } catch (Exception e) {
                // Откатываем транзакцию только если она ещё активна
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        } // После выхода из try-with-resources сессия автоматически закрывается
    
        return user;
    }
    
    public void addUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    
}