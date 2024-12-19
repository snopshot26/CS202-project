package org.example.service;

import java.util.List;

import org.example.dao.BookingDAO;
import org.example.model.Booking;

public class BookingService {
    private final BookingDAO bookingDAO = new BookingDAO();


    public Booking getBooking(long bookingId){
        return this.bookingDAO.getBookingById(bookingId);
    }

    public List<Booking> getAllBookings(){
        return this.bookingDAO.getAllBookings();
    }

    public void updateBooking(Booking booking) {
        this.bookingDAO.updateBooking(booking);
    }
    
}
