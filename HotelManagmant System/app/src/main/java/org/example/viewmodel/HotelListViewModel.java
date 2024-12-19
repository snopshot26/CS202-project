package org.example.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Hotel;
import org.example.service.HotelService;

import java.time.LocalDate;
import java.util.List;

public class HotelListViewModel {
    private final HotelService hotelService;
    private final ObservableList<Hotel> hotels;
    private final long guestId;

    public HotelListViewModel(long guestId) {
        this.hotelService = new HotelService();
        this.hotels = FXCollections.observableArrayList();
        this.guestId = guestId;
    }

    public void loadHotels() {
        List<Hotel> hotelList = hotelService.getAllHotels();
        hotels.clear();
        hotels.addAll(hotelList);
    }

    public void loadAvailableHotels(LocalDate checkIn, LocalDate checkOut) {
        List<Hotel> availableHotelList = hotelService.getAvailableHotels(checkIn, checkOut);
        hotels.clear();
        hotels.addAll(availableHotelList);
    }

    public ObservableList<Hotel> getHotels() {
        return hotels;
    }
}
