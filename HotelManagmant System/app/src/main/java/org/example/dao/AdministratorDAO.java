package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Administrator;
import org.hibernate.Session;

public class AdministratorDAO {

    public Administrator getAdministratorById(Long administratorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Administrator.class, administratorId);
        }
    }

    public List<Administrator> getAllAdministrators(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Administrator",Administrator.class).list();
        }
    }
}