package org.example.service;

import java.util.List;

import org.example.dao.AdministratorDAO;
import org.example.model.Administrator;

public class AdministratorService {
    private final AdministratorDAO AdministratorDAO = new AdministratorDAO();

    public Administrator getAdministrator(long AdministratorId){
        return this.AdministratorDAO.getAdministratorById(AdministratorId);
    }

    public List<Administrator> getAllAdministrators(){
        return this.AdministratorDAO.getAllAdministrators();
    }
}
