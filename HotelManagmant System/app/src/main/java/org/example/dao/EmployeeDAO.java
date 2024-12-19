package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Employee;
import org.hibernate.Session;

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
}