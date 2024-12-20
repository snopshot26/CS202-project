package org.example.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.example.config.HibernateUtil;
import org.example.enums.EmployeeRole;
import org.example.model.Employee;
import org.example.model.Hotel;
import org.example.model.Housekeeping;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

public class EmployeeDAO {

    public Employee getEmployeeById(Long employeeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, employeeId);
        }
    }

    public List<Employee> getAllEmployees(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Employee",Employee.class).list();
        }
    }

    public Hotel getHotelByEmployeeId(long employeeId) {
        Transaction transaction = null;
        Hotel hotel = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "SELECT e.hotel FROM Employee e WHERE e.id = :employeeId";
            Query<Hotel> query = session.createQuery(hql, Hotel.class);
            query.setParameter("employeeId", employeeId);
            hotel = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return hotel;
    }

     public List<Employee> getEmployeesByHotelIdAndRole(long hotelId, EmployeeRole role) {
        Transaction transaction = null;
        List<Employee> employees = List.of();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            String hql = "FROM Employee e WHERE e.hotel.id = :hotelId AND e.role = :role";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("hotelId", hotelId);
            query.setParameter("role", role);
            
            employees = query.getResultList();
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // В зависимости от требований, можно выбросить исключение или вернуть пустой список
        }
        return employees;
    }

    public List<Housekeeping> getHousekeepingByHotelId(long hotelId) {
        Transaction transaction = null;
        List<Housekeeping> housekeepers = List.of();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Запрос для получения всех работников с ролью HOUSEKEEPING в отеле
            String hql = "SELECT e.user FROM Employee e WHERE e.hotel.id = :hotelId AND e.role = :role";
            List<?> users = session.createQuery(hql)
                    .setParameter("hotelId", hotelId)
                    .setParameter("role", EmployeeRole.HOUSEKEEPING)
                    .getResultList();

            // Преобразуем User в Housekeeping (если это Housekeeping)
            housekeepers = users.stream()
                    .filter(user -> user instanceof Housekeeping)
                    .map(user -> (Housekeeping) user)
                    .collect(Collectors.toList());

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return housekeepers;
    }
    
}