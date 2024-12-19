package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Booking;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class BookingDAO {

    public Booking getBookingById(Long bookingId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Booking.class, bookingId);
        }
    }

    public List<Booking> getAllBookings(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Booking",Booking.class).list();
        }
    }

    public void updateBooking(Booking booking) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(booking);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
