package org.example.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.example.enums.BookingStatus;
import org.example.enums.PaymentStatus;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingID")
    private long Id;

    @ManyToOne
    @JoinColumn(name = "hotelID", referencedColumnName = "hotelID")
    private Hotel hotel;  

    @ManyToOne
    @JoinColumn(name = "guestID", referencedColumnName = "userID")
    private Guest guest;  

    @ManyToOne
    @JoinColumn(name = "roomID", referencedColumnName = "roomID")
    private Room room;  

    @Column(name ="checkInDate",nullable = false)
    private LocalDate checkInDate;

    @Column(name = "checkOutDate",nullable = false)
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private BookingStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "paymentStatus", nullable = false)
    private PaymentStatus paymentStatus;

    public Booking() {}

    public Booking(Hotel hotel,Guest guest, Room room, LocalDate checkInDate2, LocalDate checkOutDate2, BookingStatus status, PaymentStatus paymentStatus) {
        this.hotel = hotel;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate2;
        this.checkOutDate = checkOutDate2;
        this.status = status;
        this.paymentStatus = paymentStatus;
    }
    
    public long geId() {
        return this.Id;
    }

    public void setId(long bookingID) {
        this.Id = bookingID;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Guest getGuest() {
        return this.guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getCheckInDate() {
        return this.checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return this.checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public BookingStatus getStatus() {
        return this.status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
