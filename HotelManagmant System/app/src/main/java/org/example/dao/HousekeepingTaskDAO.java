package org.example.dao;

import org.example.config.HibernateUtil;
import org.example.enums.*;
import org.example.model.HousekeepingTask;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HousekeepingTaskDAO {

    public void addTask(HousekeepingTask task) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public List<HousekeepingTask> getTasksByHousekeeperId(Long housekeeperId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM HousekeepingTask ht WHERE ht.housekeeper.id = :housekeeperId ORDER BY ht.taskDate ASC";
            Query<HousekeepingTask> query = session.createQuery(hql, HousekeepingTask.class);
            query.setParameter("housekeeperId", housekeeperId);
            return query.list();
        }
    }

    public List<HousekeepingTask> getAllTasks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM HousekeepingTask";
            Query<HousekeepingTask> query = session.createQuery(hql, HousekeepingTask.class);
            return query.list();
        }
    }

    public List<HousekeepingTask> getPendingTasks(long housekeeperId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM HousekeepingTask t WHERE t.housekeeper.id = :housekeeperId AND t.status = :status";
            Query<HousekeepingTask> query = session.createQuery(hql, HousekeepingTask.class);
            query.setParameter("housekeeperId", housekeeperId);
            query.setParameter("status", TaskStatus.PENDING);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<HousekeepingTask> getCompletedTasks(long housekeeperId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM HousekeepingTask t WHERE t.housekeeper.id = :housekeeperId AND t.status = :status";
            Query<HousekeepingTask> query = session.createQuery(hql, HousekeepingTask.class);
            query.setParameter("housekeeperId", housekeeperId);
            query.setParameter("status", TaskStatus.COMPLETED);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public boolean updateTaskStatusToCompleted(long taskId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            HousekeepingTask task = session.get(HousekeepingTask.class, taskId);
            if (task != null) {
                task.setStatus(TaskStatus.COMPLETED);
                session.update(task);
                transaction.commit();
                return true;
            }
            return false; // Task not found
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<HousekeepingTask> getCleaningSchedule(long housekeeperId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM HousekeepingTask t WHERE t.housekeeper.id = :housekeeperId ORDER BY t.scheduledDate ASC";
            Query<HousekeepingTask> query = session.createQuery(hql, HousekeepingTask.class);
            query.setParameter("housekeeperId", housekeeperId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
