package org.example.viewmodel;

import org.example.dao.BookingDAO;

import java.util.Map;

public class ViewMostBookedRoomTypesViewModel {
    private final BookingDAO bookingDAO;

    public ViewMostBookedRoomTypesViewModel() {
        this.bookingDAO = new BookingDAO();
    }

    public Map<String, Integer> getMostBookedRoomTypes() {
        return bookingDAO.getMostBookedRoomTypes();
    }
}
