package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Receptionist;
import org.hibernate.Session;

public class ReceptionistDAO {
    public Receptionist getReceptionistById(long receptionistId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Receptionist.class, receptionistId);
        }
    }

    public List<Receptionist> getAllReceptionists(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Receptionist",Receptionist.class).list();
        }
    }

    public boolean addReceptionist(Receptionist receptionist) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(receptionist);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteReceptionist(Long receptionistId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Receptionist receptionist = session.get(Receptionist.class, receptionistId);
            if (receptionist == null) {
                return false;
            }
            session.beginTransaction();
            session.delete(receptionist);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

