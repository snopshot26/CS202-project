package org.example.service;

import java.util.List;

import org.example.dao.GuestDAO;
import org.example.model.Guest;

public class GuestService {
    private final GuestDAO guestDAO = new GuestDAO();

    public Guest getGuest(long GuestId){
        return this.guestDAO.getGuestById(GuestId);
    }

    public List<Guest> getAllGuests(){
        return this.guestDAO.getAllGuests();
    }

    public void updateGuest(Guest guest) {
        guestDAO.updateGuest(guest);
    }
}
