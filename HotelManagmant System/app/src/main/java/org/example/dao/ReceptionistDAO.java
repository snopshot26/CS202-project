package org.example.dao;

import java.util.List;

import org.example.config.HibernateUtil;
import org.example.model.Receptionist;
import org.hibernate.Session;

public class ReceptionistDAO {
    public Receptionist getReceptionistById(long receptionistId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Receptionist.class, receptionistId);
        }
    }

    public List<Receptionist> getAllReceptionists(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Receptionist",Receptionist.class).list();
        }
    }
}
