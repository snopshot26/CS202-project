package org.example.service;

import java.time.LocalDate;
import java.util.List;

import org.example.dao.HotelDAO;
import org.example.model.Hotel;

public class HotelService {
    private final HotelDAO HotelDAO = new HotelDAO();

    public Hotel getHotel(long HotelId){
        return this.HotelDAO.getHotelById(HotelId);
    }

    public List<Hotel> getAllHotels(){
        return this.HotelDAO.getAllHotels();
    }

    public List<Hotel> getAvailableHotels(LocalDate checkIn, LocalDate checkOut) {
        return this.HotelDAO.getAvailableHotels(checkIn, checkOut); 
    }
}
