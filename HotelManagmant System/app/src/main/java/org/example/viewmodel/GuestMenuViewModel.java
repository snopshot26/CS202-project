package org.example.viewmodel;

import java.util.List;

import org.example.model.Booking;
import org.example.service.BookingService;
import org.example.service.GuestService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GuestMenuViewModel {
    private final GuestService guestService;
    private final BookingService bookingService;
    private final ObservableList<Booking> myBookings;

    public GuestMenuViewModel() {
        this.guestService = new GuestService();
        this.bookingService = new BookingService();
        this.myBookings = FXCollections.observableArrayList();
    }

    public ObservableList<Booking> getMyBookings(Long guestId) {
        List<Booking> bookings = bookingService.getAllBookings(); 
        myBookings.clear();

        // Фильтрация бронирований для конкретного гостя
        for (Booking booking : bookings) {
            if (booking.getGuest().getId() == guestId) {
                myBookings.add(booking);
            }
        }
        return myBookings;
    }
}
