package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public void addPayment(Payment payment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(payment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}