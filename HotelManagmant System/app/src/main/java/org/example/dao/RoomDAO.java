package org.example.dao;

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

    // public List<Room> getAvailableRooms() {
    //     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    //         return session.createQuery("FROM Room WHERE status = 'available'", Room.class).list();
    //     }
    // }

    // public void saveRoom(Room room) {
    //     Transaction transaction = null;
    //     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    //         transaction = session.beginTransaction();
    //         session.save(room);
    //         transaction.commit();
    //     } catch (Exception e) {
    //         if (transaction != null) transaction.rollback();
    //         throw e;
    //     }
    // }

    // public void deleteRoom(Long roomId) {
    //     Transaction transaction = null;
    //     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    //         transaction = session.beginTransaction();
    //         Room room = session.get(Room.class, roomId);
    //         if (room != null) {
    //             session.delete(room);
    //         }
    //         transaction.commit();
    //     } catch (Exception e) {
    //         if (transaction != null) transaction.rollback();
    //         throw e;
    //     }
    // }
}
