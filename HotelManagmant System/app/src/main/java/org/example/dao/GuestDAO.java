package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Guest;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GuestDAO {

    public Guest getGuestById(Long guestId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Guest.class, guestId);
        }
    }

    public List<Guest> getAllGuests(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Guest",Guest.class).list();
        }
    }

    public void updateGuest(Guest guest) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(guest);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}