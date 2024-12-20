package org.example.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Booking;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


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

    public List<Booking> getBookingsByGuestId(long guestId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Booking b WHERE b.guest.id = :guestId", Booking.class)
                          .setParameter("guestId", guestId)
                          .list();
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

    public void addBooking(Booking booking) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(booking);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Booking> getBookingsByHotel(long hotelId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Booking> query = session.createQuery(
                "SELECT b FROM Booking b WHERE b.hotel.id = :hotelId", Booking.class
            );
            query.setParameter("hotelId", hotelId);
            return query.list();
        }
    }
    
    public List<Booking> getOverlappingBookings(long roomId, LocalDate checkIn, LocalDate checkOut) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Booking b WHERE b.room.id = :roomId " +
                "AND b.status <> 'CANCELED' " +
                "AND b.checkInDate < :checkOut " +
                "AND b.checkOutDate > :checkIn",
                Booking.class
            )
            .setParameter("roomId", roomId)
            .setParameter("checkIn", checkIn)
            .setParameter("checkOut", checkOut)
            .list();
        }
    }
    
    public double calculateRevenue(LocalDate startDate, LocalDate endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(r.price) FROM Booking b JOIN Room r ON b.roomID = r.roomID " +
                         "WHERE b.checkInDate >= :startDate AND b.checkOutDate <= :endDate AND b.status = 'CONFIRMED'";
            Query<Double> query = session.createQuery(hql, Double.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            Double result = query.uniqueResult();
            return result != null ? result : 0.0;
        }
    }

    public Map<String, Integer> getMostBookedRoomTypes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT r.roomType, COUNT(b.bookingId) FROM Booking b JOIN Room r ON b.roomID = r.roomID " +
                         "GROUP BY r.roomType ORDER BY COUNT(b.bookingId) DESC";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            List<Object[]> results = query.list();

            Map<String, Integer> roomTypeBookings = new HashMap<>();
            for (Object[] row : results) {
                String roomType = (String) row[0];
                Long count = (Long) row[1];
                roomTypeBookings.put(roomType, count.intValue());
            }

            return roomTypeBookings;
        }
    }

    public List<Booking> getBookingsByRoom(Long roomId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Booking b WHERE b.room.roomId = :roomId";
            Query<Booking> query = session.createQuery(hql, Booking.class);
            query.setParameter("roomId", roomId);
            return query.list();
        }
    }

    public boolean hasActiveBookings(Long roomId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "SELECT COUNT(b.id) FROM Booking b WHERE b.room.id = :roomId AND b.status = 'CONFIRMED'";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("roomId", roomId);
            Long count = query.uniqueResult();
            transaction.commit();
            return count != null && count > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

}
