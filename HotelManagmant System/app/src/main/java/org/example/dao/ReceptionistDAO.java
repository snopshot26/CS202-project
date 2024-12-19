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

    public void addReceptionist(Receptionist receptionist) {
        this.receptionistDAO.addReceptionist(receptionist);
    }

    public void modifyReceptionist(Receptionist receptionist) {
        this.receptionistDAO.updateReceptionist(receptionist);
    }

    public void deleteReceptionist(long receptionistId) {
        this.receptionistDAO.deleteReceptionist(receptionistId);
    }

    public Receptionist getReceptionistById(long receptionistId) {
        return this.receptionistDAO.getReceptionistById(receptionistId);
    }

    public List<Receptionist> getAllReceptionists() {
        return this.receptionistDAO.getAllReceptionists();
    }
   
}

