package org.example.viewmodel;

import org.example.enums.BookingStatus;
import org.example.enums.PaymentMethod;
import org.example.enums.PaymentStatus;
import org.example.model.Booking;
import org.example.model.Hotel;
import org.example.model.Payment;
import org.example.model.Room;
import org.example.model.Guest; // предполагается, что у вас есть класс Guest
import org.example.service.BookingService;
import org.example.service.PaymentService;
import org.example.service.UserService;

import java.time.LocalDate;

public class PaymentViewModel {
    private final long guestId;
    private final Hotel hotel;
    private final Room room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final UserService userService;

    public PaymentViewModel(long guestId, Hotel hotel, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.guestId = guestId;
        this.hotel = hotel;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingService = new BookingService();
        this.paymentService = new PaymentService();
        this.userService = new UserService();
    }

    public boolean proceedPayment(PaymentMethod method) {
        try {
            // Находим гостя по guestId
            Guest guest = (Guest) userService.getUser(guestId); // Или другой метод для получения гостя

            // Определяем paymentStatus в зависимости от метода
            PaymentStatus paymentStatus;
            switch (method) {
                case CREDIT_CARD:
                case DEBIT_CARD:
                case ONLINE:
                    paymentStatus = PaymentStatus.COMPLETED;
                    break;
                case CASH:
                default:
                    paymentStatus = PaymentStatus.PENDING;
                    break;
            }

            // Создаём бронирование
            Booking booking = new Booking(
                hotel,
                guest,
                room,
                checkInDate,
                checkOutDate,
                BookingStatus.CONFIRMED,
                paymentStatus
            );

            bookingService.addBooking(booking);

            // Создаём Payment
            Payment payment = new Payment();
            payment.setBooking(booking);
            payment.setAmount(room.getPrice()); // Предполагается, что у Room есть метод getPrice()
            payment.setPaymentDate(LocalDate.now());
            payment.setPaymentMethod(method);
            payment.setPaymentStatus(paymentStatus);

            paymentService.addPayment(payment);

            

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
