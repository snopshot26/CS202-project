package org.example.service;

import java.util.List;

import org.example.dao.GuestDAO;
import org.example.model.Guest;

public class GuestService {
    private final GuestDAO GuestDAO = new GuestDAO();

    public Guest getGuest(long GuestId){
        return this.GuestDAO.getGuestById(GuestId);
    }

    public List<Guest> getAllGuests(){
        return this.GuestDAO.getAllGuests();
    }
}
