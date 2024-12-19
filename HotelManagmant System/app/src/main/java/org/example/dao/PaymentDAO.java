package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Payment;
import org.hibernate.Session;

public class PaymentDAO {
    public Payment getPaymentById(long paymentId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Payment.class, paymentId);
        }
    }

    public List<Payment> getAllPayments(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Payment",Payment.class).list();
        }
    }
}