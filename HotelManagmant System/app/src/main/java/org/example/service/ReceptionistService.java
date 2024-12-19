package org.example.service;

import java.util.List;

import org.example.dao.ReceptionistDAO;
import org.example.model.Receptionist;

public class ReceptionistService {
    private final ReceptionistDAO ReceptionistDAO = new ReceptionistDAO();

    public Receptionist getReceptionist(long ReceptionistId){
        return this.ReceptionistDAO.getReceptionistById(ReceptionistId);
    }

    public List<Receptionist> getAllReceptionists(){
        return this.ReceptionistDAO.getAllReceptionists();
    }
}
