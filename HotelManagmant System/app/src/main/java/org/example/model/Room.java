package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.example.enums.RoomStatus;
import org.example.enums.RoomType;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="roomID")
    private long Id;

    @ManyToOne
    @JoinColumn(name = "hotelID", referencedColumnName = "hotelID")
    private Hotel hotel; 

    @Column(name = "roomNumber", nullable = false, length = 10)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "roomType",nullable = false)
    private RoomType roomType;

    @Column(name = "price", nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private RoomStatus status;


    public Room() {
    }

    public Room(Hotel hotel, String roomNumber, RoomType roomType, double price, RoomStatus status) {
        this.hotel = hotel;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.status = status;
    }


    public long getId() {
        return this.Id;
    }

    public void setId(long roomID) {
        this.Id = roomID;
    }

    public Hotel getHotel() {
        return this.hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RoomStatus getStatus() {
        return this.status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}

