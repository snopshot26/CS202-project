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

    package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.HousekeepingSchedule;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HousekeepingScheduleDAO {

    public void addSchedule(HousekeepingSchedule schedule) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(schedule);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateSchedule(HousekeepingSchedule schedule) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(schedule);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteSchedule(long scheduleId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            HousekeepingSchedule schedule = session.get(HousekeepingSchedule.class, scheduleId);
            if (schedule != null) {
                session.delete(schedule);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public HousekeepingSchedule getScheduleById(long scheduleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(HousekeepingSchedule.class, scheduleId);
        }
    }

    public List<HousekeepingSchedule> getAllSchedules() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM HousekeepingSchedule", HousekeepingSchedule.class).list();
        }
    }
}

