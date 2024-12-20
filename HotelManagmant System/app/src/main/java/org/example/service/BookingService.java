package org.example.service;

import java.time.LocalDate;
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

    public List<Booking> getGuestBookings(long guestId) {
        return bookingDAO.getBookingsByGuestId(guestId);
    }
    
    public void addBooking(Booking booking) {
        bookingDAO.addBooking(booking);
    }
    
    public List<Booking> getBookingsByHotel(long hotelId) {
        return bookingDAO.getBookingsByHotel(hotelId);
    }

    public boolean isRoomAvailable(long roomId, LocalDate checkIn, LocalDate checkOut) {
        List<Booking> overlappingBookings = bookingDAO.getOverlappingBookings(roomId, checkIn, checkOut);
        return overlappingBookings.isEmpty();
    }
    
    public double calculateRevenue(LocalDate startDate, LocalDate endDate) {
        return bookingDAO.calculateRevenue(startDate, endDate);
    }

    public List<Booking> getBookingsByRoom(long roomId) {
        return bookingDAO.getBookingsByRoom(roomId);
    }

    public boolean hasActiveBookings(long roomId) {
        return bookingDAO.hasActiveBookings(roomId);
    }
}
