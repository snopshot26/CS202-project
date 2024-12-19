package org.example.dao;

import java.time.LocalDate;
import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Room;
import org.hibernate.Session;

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
}
