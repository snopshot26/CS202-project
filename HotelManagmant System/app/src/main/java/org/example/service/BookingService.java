package org.example.service;

import org.example.dao.BookingDAO;
import org.example.model.Booking;
import java.util.List;

public class BookingService {
    private final BookingDAO bookingDAO = new BookingDAO();


    public Booking getBooking(long bookingId){
        return this.bookingDAO.getBookingById(bookingId);
    }

    public List<Booking> getAllBookings(){
        return this.bookingDAO.getAllBookings();
    }

    // // Добавление нового бронирования
    // public void addNewBooking(long guestID, long roomID, long hotelID, LocalDate checkInDate, LocalDate checkOutDate) {
    //     if (checkInDate.isAfter(checkOutDate)) {
    //         throw new IllegalArgumentException("Check-in date cannot be after check-out date.");
    //     }
    
    //     // Создаём объект Booking
    //     Booking booking = new Booking();
    //     Guest guest = new Guest();
    //     Room room = new Room();
    //     Hotel hotel = new Hotel();
    
    //     guest.setId(guestID);
    //     room.setId(roomID);
    //     hotel.setId(hotelID);
    
    //     booking.setGuest(guest);
    //     booking.setRoom(room);
    //     booking.setHotel(hotel);
    //     booking.setCheckInDate(checkInDate);
    //     booking.setCheckOutDate(checkOutDate);
    //     booking.setStatus(BookingStatus.PENDING);
    //     booking.setPaymentStatus(PaymentStatus.PENDING);
    
    //     // Передаём в DAO
    //     bookingDAO.addNewBooking(booking);
    // }
    


    // // Получение всех доступных комнат
    // public List<Room> getAvailableRooms() {
    //     return bookingDAO.viewAvailableRooms();
    // }

    // // Получение всех бронирований гостя
    // public List<Booking> getMyBookings(int guestID) {
    //     return bookingDAO.viewMyBookings(guestID);
    // }

    // // Отмена бронирования
    // public boolean cancelBooking(int bookingID) {
    //     boolean result = bookingDAO.cancelBooking(bookingID);
    //     if (!result) {
    //         throw new RuntimeException("Failed to cancel booking with ID: " + bookingID);
    //     }
    //     return true;
    // }

    // public void modifyBooking(Booking booking) {
    //     bookingDAO.updateBooking(booking);
    // }

    // public void deleteBooking(Long bookingId) {
    //     bookingDAO.deleteBooking(bookingId);
    // }

    // public Booking viewBooking(Long bookingId) {
    //     return bookingDAO.getBookingById(bookingId);
    // }

    // public List<Booking> viewAllBookings() {
    //     return bookingDAO.getAllBookings();
    // }
}
