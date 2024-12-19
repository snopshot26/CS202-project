package org.example.service;

import java.util.List;

import org.example.dao.PaymentDAO;
import org.example.model.Payment;

public class PaymentService {
    private final PaymentDAO PaymentDAO = new PaymentDAO();

    public Payment getPayment(long PaymentId){
        return this.PaymentDAO.getPaymentById(PaymentId);
    }

    public List<Payment> getAllPayments(){
        return this.PaymentDAO.getAllPayments();
    }

    public void addPayment(Payment payment) {
        PaymentDAO.addPayment(payment);
    }

}
