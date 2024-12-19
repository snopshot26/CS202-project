package org.example.dao;


import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.HousekeepingSchedule;
import org.hibernate.Session;

public class HousekeepingScheduleDAO {
    public HousekeepingSchedule getHousekeepingScheduleById(long housekeepingScheduleId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(HousekeepingSchedule.class, housekeepingScheduleId);
        }
    }

    public List<HousekeepingSchedule> getAllHousekeepingSchedules(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM HousekeepingSchedule",HousekeepingSchedule.class).list();
        }
    }
}
