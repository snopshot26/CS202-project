package org.example.viewmodel;

import org.example.enums.BookingStatus;
import org.example.enums.PaymentMethod;
import org.example.enums.PaymentStatus;
import org.example.model.*;
import org.example.service.BookingService;
import org.example.service.PaymentService;
import org.example.service.UserService;

import java.time.LocalDate;

import org.example.enums.UserType;

public class PaymentViewModel {
    private final Hotel hotel;
    private final Room room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final UserService userService;

    private final long userId; // Может быть guestId или receptionistId
    private Guest guest; // Для гостя или создаваемого гостя
    private boolean isReceptionist;

    public PaymentViewModel(long userId, Hotel hotel, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingService = new BookingService();
        this.paymentService = new PaymentService();
        this.userService = new UserService();

        this.userId = userId;
        this.hotel = hotel;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;

        initializeUser();
    }

    private void initializeUser() {
        User user = userService.getUser(userId);
        if (user instanceof Guest) {
            this.guest = (Guest) user;
            this.isReceptionist = false;
        } else if (user instanceof Receptionist) {
            this.isReceptionist = true;
        } else {
            throw new IllegalArgumentException("Unsupported user type");
        }
    }


    public Guest createGuest(String name, String email, String phoneNumber, String password) {
        Guest newGuest = new Guest(name, email, phoneNumber, password, UserType.GUEST, 0); 
        userService.addGuest(newGuest);
        this.guest = newGuest;
        return newGuest;
    }


    public boolean proceedPayment(PaymentMethod method) {
        try {
            // Если пользователь — ресепшионист, убедимся, что гостя создали
            if (isReceptionist && guest == null) {
                throw new IllegalStateException("Guest information is required for receptionist bookings.");
            }

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

    public boolean isReceptionist() {
        return isReceptionist;
    }
}
