package org.example.dao;

import java.time.LocalDate;
import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RoomDAO {
    

    public Room getRoomById(long roomId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Room.class, roomId);
        }
    }
    public List<Room> getAllRooms(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Room",Room.class).list();
        }
    }

    public List<Room> getRoomsByHotelId(long hotelId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Room r WHERE r.hotel.id = :hotelId", Room.class)
                          .setParameter("hotelId", hotelId)
                          .list();
        }
    }

    public List<Room> getAvailableRooms(long hotelId, LocalDate checkIn, LocalDate checkOut) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Находим занятые комнаты данного отеля на этот период
            List<Long> occupiedRoomIds = session.createQuery(
                "SELECT DISTINCT b.room.id FROM Booking b " +
                "WHERE b.room.hotel.id = :hotelId " +
                "AND b.status <> 'CANCELED' " +
                "AND b.checkInDate < :checkOut " +
                "AND b.checkOutDate > :checkIn", Long.class)
                .setParameter("hotelId", hotelId)
                .setParameter("checkIn", checkIn)
                .setParameter("checkOut", checkOut)
                .list();

            if (occupiedRoomIds.isEmpty()) {
                // Нет занятых комнат, значит все комнаты отеля доступны
                return session.createQuery("FROM Room r WHERE r.hotel.id = :hotelId", Room.class)
                        .setParameter("hotelId", hotelId)
                        .list();
            }

            // Возвращаем комнаты отеля, которые НЕ в списке занятых
            return session.createQuery(
                "FROM Room r " +
                "WHERE r.hotel.id = :hotelId " +
                "AND r.id NOT IN (:occupiedRoomIds)", Room.class)
                .setParameter("hotelId", hotelId)
                .setParameterList("occupiedRoomIds", occupiedRoomIds)
                .list();
        }
    }

    public Room getRoomByNumberAndHotel(String roomNumber, Long hotelId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Room r WHERE r.roomNumber = :roomNumber AND r.hotel.id = :hotelId"; // Изменено 'hotelID' на 'id'
            Query<Room> query = session.createQuery(hql, Room.class);
            query.setParameter("roomNumber", roomNumber);
            query.setParameter("hotelId", hotelId);
            return query.uniqueResult();
        }
    }

    public boolean addRoom(Room room) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(room);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRoom(Room room) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(room);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRoom(Long roomId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Получаем объект Room по его ID
            Room room = session.get(Room.class, roomId);
            if (room == null) {
                System.out.println("Комната с ID " + roomId + " не найдена.");
                return false;
            }

            // Удаляем комнату
            session.delete(room);

            // Подтверждаем транзакцию
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Откатываем транзакцию в случае ошибки
            }
            e.printStackTrace();
            return false;
        }
    }
}
