package org.example.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Room;
import org.example.service.RoomService;

import java.time.LocalDate;
import java.util.List;

public class RoomListViewModel {
    private final RoomService roomService;
    private final ObservableList<Room> rooms;
    private final long hotelId;

    public RoomListViewModel(long hotelId) {
        this.roomService = new RoomService();
        this.rooms = FXCollections.observableArrayList();
        this.hotelId = hotelId;
    }

    public void loadAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        List<Room> availableRooms = roomService.getAvailableRooms(hotelId, checkIn, checkOut);
        rooms.clear();
        rooms.addAll(availableRooms);
    }

    public ObservableList<Room> getRooms() {
        return rooms;
    }
}
