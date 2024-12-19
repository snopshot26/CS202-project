package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Guest;
import org.hibernate.Session;

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
}