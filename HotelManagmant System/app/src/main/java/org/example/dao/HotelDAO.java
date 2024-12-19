package org.example.dao;

import java.time.LocalDate;
import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Hotel;
import org.hibernate.Session;

public class HotelDAO {
    public Hotel getHotelById(long hotelId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Hotel.class, hotelId);
        }
    }

    public List<Hotel> getAllHotels(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Hotel",Hotel.class).list();
        }
    }

    public List<Hotel> getAvailableHotels(LocalDate checkIn, LocalDate checkOut) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Находим идентификаторы занятых комнат в указанный период
            List<Long> occupiedRoomIds = session.createQuery(
                "SELECT DISTINCT b.room.id FROM Booking b " +
                "WHERE b.status <> 'CANCELED' " +
                "AND b.checkInDate < :checkOut " +
                "AND b.checkOutDate > :checkIn", Long.class)
                .setParameter("checkIn", checkIn)
                .setParameter("checkOut", checkOut)
                .list();
    
            if (occupiedRoomIds.isEmpty()) {
                // Нет занятых комнат, все отели доступны
                return session.createQuery("FROM Hotel", Hotel.class).list();
            }
    
            // Используем подзапрос, чтобы найти отели, у которых есть хотя бы одна комната
            // вне списка занятых комнат (т.е. свободная комната).
            return session.createQuery(
                "SELECT DISTINCT h " +
                "FROM Hotel h " +
                "WHERE h.id IN (" +
                "  SELECT r.hotel.id FROM Room r " +
                "  WHERE r.hotel.id = h.id AND r.id NOT IN (:occupiedRoomIds)" +
                ")",
                Hotel.class
            )
            .setParameterList("occupiedRoomIds", occupiedRoomIds)
            .list();
        }
    }
    

}