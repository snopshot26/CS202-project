package org.example.dao;

import java.time.LocalDate;
import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Housekeeping;
import org.hibernate.Session;
import org.hibernate.query.Query;

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

    public List<Housekeeping> getAvailableHousekeepers(LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Housekeeping h WHERE h.id NOT IN (" +
                    "SELECT ht.housekeeper.id FROM HousekeepingTask ht WHERE ht.taskDate = :date)";
            Query<Housekeeping> query = session.createQuery(hql, Housekeeping.class);
            query.setParameter("date", date);
            return query.list();
        }
    }
}


