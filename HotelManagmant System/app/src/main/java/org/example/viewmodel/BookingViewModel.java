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

    public BookingViewModel() {
        this.bookingService = new BookingService();
        this.bookings = FXCollections.observableArrayList();
        loadBookings();
    }

    // Загружаем все бронирования
    public void loadBookings() {
        List<Booking> allBookings = bookingService.getAllBookings();
        bookings.clear();
        bookings.addAll(allBookings);
    }

    // Отмена бронирования
    public void cancelBooking(Booking booking) {
        if (booking != null) {
            booking.setStatus(BookingStatus.CANCELED); // Устанавливаем статус "CANCELED"
            // Здесь должна быть логика для сохранения в базе данных
            bookingService.updateBooking(booking);
            loadBookings(); // Обновляем список после отмены
        }
    }

    public ObservableList<Booking> getBookings() {
        return bookings;
    }
}
