package org.example.dao;

import org.example.config.HibernateUtil;
import org.example.model.Booking;
import org.hibernate.Session;
import java.util.List;


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

    // public List<Booking> getAllBookings() {
    //     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    //         return session.createQuery("from Booking", Booking.class).list();
    //     }
    // }

    // // Добавление нового бронирования
    // public void addNewBooking(Booking booking) {
    //     Transaction transaction = null;
    //     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    //         transaction = session.beginTransaction();

    //         // Получаем необходимые сущности из базы
    //         Guest guest = session.get(Guest.class, booking.getGuest().getId());
    //         Room room = session.get(Room.class, booking.getRoom().getId());
    //         Hotel hotel = session.get(Hotel.class, booking.getHotel().getId());

    //         // Проверяем, что сущности существуют
    //         if (guest == null || room == null || hotel == null) {
    //             throw new IllegalArgumentException("Guest, Room, or Hotel not found!");
    //         }

    //         // Устанавливаем связи в объекте Booking
    //         booking.setGuest(guest);
    //         booking.setRoom(room);
    //         booking.setHotel(hotel);

    //         // Сохраняем бронирование
    //         session.save(booking);

    //         // Обновляем статус комнаты на "booked"
    //         room.setStatus(RoomStatus.BOOKED);
    //         session.update(room);

    //         transaction.commit();
    //         System.out.println("Booking added successfully!");
    //     } catch (Exception e) {
    //         if (transaction != null) transaction.rollback();
    //         e.printStackTrace();
    //         throw new RuntimeException("Failed to add new booking", e);
    //     }
    // }

    // public void updateBooking(Booking booking) {
    //     Transaction transaction = null;
    //     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    //         transaction = session.beginTransaction();
    //         session.update(booking);
    //         transaction.commit();
    //     } catch (Exception e) {
    //         if (transaction != null) transaction.rollback();
    //         e.printStackTrace();
    //     }
    // }


    // // Получение всех доступных комнат
    // public List<Room> viewAvailableRooms() {
    //     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    //         String hql = "FROM Room WHERE status = :status";
    //         return session.createQuery(hql, Room.class)
    //                       .setParameter("status", RoomStatus.AVAILABLE)
    //                       .list();
    //     } catch (Exception e) {
    //         throw new RuntimeException("Failed to fetch available rooms", e);
    //     }
    // }

    // // Получение всех бронирований гостя
    // public List<Booking> viewMyBookings(int guestID) {
    //     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    //         String hql = "FROM Booking WHERE guest.userID = :guestID";
    //         return session.createQuery(hql, Booking.class)
    //                       .setParameter("guestID", guestID)
    //                       .list();
    //     } catch (Exception e) {
    //         throw new RuntimeException("Failed to fetch guest bookings", e);
    //     }
    // }

    // public void deleteBooking(Long bookingId) {
    //     Transaction transaction = null;
    //     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    //         transaction = session.beginTransaction();
    //         Booking booking = session.get(Booking.class, bookingId);
    //         if (booking != null) {
    //             session.delete(booking);
    //             System.out.println("Booking deleted!");
    //         }
    //         transaction.commit();
    //     } catch (Exception e) {
    //         if (transaction != null) transaction.rollback();
    //         e.printStackTrace();
    //     }
    // }

    // // Отмена бронирования
    // public boolean cancelBooking(int bookingID) {
    //     Transaction transaction = null;
    //     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    //         transaction = session.beginTransaction();

    //         Booking booking = session.get(Booking.class, bookingID);
    //         if (booking != null && booking.getStatus() != BookingStatus.CANCELED) {
    //             booking.setStatus(BookingStatus.CANCELED);
    //             session.update(booking);

    //             // Освобождаем комнату
    //             Room room = session.get(Room.class, booking.getRoom().getId());
    //             if (room != null) {
    //                 room.setStatus(RoomStatus.AVAILABLE);
    //                 session.update(room);
    //             }
    //             transaction.commit();
    //             return true;
    //         }
    //         transaction.rollback();
    //         return false;
    //     } catch (Exception e) {
    //         if (transaction != null) transaction.rollback();
    //         e.printStackTrace();
    //         return false;
    //     }
    // }
}
