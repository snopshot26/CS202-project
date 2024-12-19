package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Housekeeping;
import org.hibernate.Session;

public class HousekeepingDAO {
    public Housekeeping getHousekeepingById(long housekeepingId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Housekeeping.class, housekeepingId);
        }
    }

    public List<Housekeeping> getAllHousekeepings(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Housekeeping",Housekeeping.class).list();
        }
    }
}
