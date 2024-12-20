package org.example.viewmodel;

import org.example.dao.BookingDAO;
import org.example.model.Booking;

import java.util.List;

public class ViewAllBookingRecordsViewModel {
    private final BookingDAO bookingDAO;

    public ViewAllBookingRecordsViewModel() {
        this.bookingDAO = new BookingDAO();
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }
}
