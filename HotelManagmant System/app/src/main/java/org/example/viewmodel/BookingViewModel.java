package org.example.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Booking;
import org.example.service.BookingService;
import org.example.enums.BookingStatus;

import java.util.List;

public class BookingViewModel {
    private final BookingService bookingService;
    private final ObservableList<Booking> bookings;
    private final long guestId;

    public BookingViewModel(long guestId) {
        this.bookingService = new BookingService();
        this.bookings = FXCollections.observableArrayList();
        this.guestId = guestId;
        loadGuestBookings(this.guestId);
    }

    public void loadGuestBookings(long guestId) {
        List<Booking> guestBookings = bookingService.getGuestBookings(guestId);
        bookings.clear();
        bookings.addAll(guestBookings);
    }

    public boolean cancelBooking(Booking booking) {
        if (booking != null) {
            if (!canCancelBooking(booking)) {
                // Нельзя отменить
                return false;
            }
            booking.setStatus(BookingStatus.CANCELED);
            bookingService.updateBooking(booking);
            loadGuestBookings(this.guestId);
            return true;
        }
        return false;
    }

    public boolean canCancelBooking(Booking booking) {
        java.time.LocalDate today = java.time.LocalDate.now();
        return !booking.getCheckInDate().isBefore(today);
    }

    public ObservableList<Booking> getBookings() {
        return this.bookings;
    }
}
