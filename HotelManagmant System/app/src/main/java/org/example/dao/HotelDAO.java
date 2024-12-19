package org.example.dao;

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
}