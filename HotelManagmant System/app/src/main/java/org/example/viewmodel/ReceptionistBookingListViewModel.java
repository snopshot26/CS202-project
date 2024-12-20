package org.example.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Booking;
import org.example.model.Hotel;
import org.example.service.BookingService;
import org.example.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;

public class ReceptionistBookingListViewModel {
    private final BookingService bookingService;
    private final EmployeeService employeeService;
    private final ObservableList<Booking> bookings;
    private final long receptionistId;
    private final Hotel hotel;

    public ReceptionistBookingListViewModel(long receptionistId) {
        this.bookingService = new BookingService();
        this.employeeService = new EmployeeService();
        this.bookings = FXCollections.observableArrayList();
        this.receptionistId = receptionistId;
        this.hotel = this.employeeService.getHotelForEmployee(receptionistId);

        loadBookingsForHotel();
    }

    // Загрузка всех бронирований для отеля, в котором работает ресепшионист
    private void loadBookingsForHotel() {
        if (this.hotel != null) {
            List<Booking> hotelBookings = bookingService.getBookingsByHotel(this.hotel.getId());
            bookings.clear();
            bookings.addAll(hotelBookings);
        } else {
            // Если hotel == null, возможно ресепшионист не привязан к отелю
            bookings.clear();
        }
    }

    public ObservableList<Booking> getBookings() {
        return bookings;
    }

    // Отмена бронирования (пример логики)
    public boolean cancelBooking(Booking booking) {
        // Нельзя отменить, если бронь в прошлом (дата заезда в прошлом)
        if (booking.getCheckInDate().isBefore(java.time.LocalDate.now())) {
            return false;
        }

        booking.setStatus(org.example.enums.BookingStatus.CANCELED);
        bookingService.updateBooking(booking);
        loadBookingsForHotel();
        return true;
    }

    public boolean modifyBooking(Booking booking, LocalDate newCheckIn, LocalDate newCheckOut) {
        // Проверяем, нельзя менять если бронь уже отменена
        if (booking.getStatus() == org.example.enums.BookingStatus.CANCELED) {
            return false;
        }
    
        // Проверяем, можно ли изменить бронирование только если оно ещё не наступило (будущее)
        // Если дата заезда уже прошла или сегодня, то изменения не допустимы
        if (!booking.getCheckInDate().isAfter(LocalDate.now())) {
            return false;
        }
    
        // Проверяем, чтобы новые даты были корректными: newCheckIn < newCheckOut и newCheckIn > сегодня
        if (newCheckIn == null || newCheckOut == null || !newCheckIn.isBefore(newCheckOut) || newCheckIn.isBefore(LocalDate.now())) {
            return false;
        }
    
        // Проверяем доступность комнаты на новые даты
        boolean available = bookingService.isRoomAvailable(booking.getRoom().getId(), newCheckIn, newCheckOut);
        if (!available) {
            return false;
        }
    
        // Если доступна, обновляем даты
        booking.setCheckInDate(newCheckIn);
        booking.setCheckOutDate(newCheckOut);
        bookingService.updateBooking(booking);
        loadBookingsForHotel();
        return true;
    }
    

    public boolean payForBooking(Booking booking) {
        // Проверяем можно ли оплатить: если статус оплаты уже COMPLETED или бронь отменена, нельзя
        if (booking.getPaymentStatus() == org.example.enums.PaymentStatus.COMPLETED ||
            booking.getStatus() == org.example.enums.BookingStatus.CANCELED) {
            return false;
        }

        // Выполняем оплату: обновляем статус оплаты
        booking.setPaymentStatus(org.example.enums.PaymentStatus.COMPLETED);
        bookingService.updateBooking(booking);
        loadBookingsForHotel();
        return true;
    }
}
