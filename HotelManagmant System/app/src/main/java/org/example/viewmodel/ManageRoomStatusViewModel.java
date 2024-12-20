package org.example.viewmodel;

import org.example.dao.HotelDAO;
import org.example.dao.RoomDAO;
import org.example.enums.RoomStatus;
import org.example.model.Hotel;
import org.example.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class ManageRoomStatusViewModel {
    private final HotelDAO hotelDAO;
    private final RoomDAO roomDAO;

    public ManageRoomStatusViewModel() {
        this.hotelDAO = new HotelDAO();
        this.roomDAO = new RoomDAO();
    }

    public List<String> getHotelNames() {
        List<Hotel> hotels = hotelDAO.getAllHotels();
        return hotels.stream().map(Hotel::getName).collect(Collectors.toList());
    }

    public List<String> getRoomNumbersByHotel(String hotelName) {
        Hotel hotel = hotelDAO.getHotelByName(hotelName);
        if (hotel == null) {
            return List.of();
        }
        List<Room> rooms = roomDAO.getRoomsByHotelId(hotel.getId());
        return rooms.stream().map(Room::getRoomNumber).collect(Collectors.toList());
    }

    public List<RoomStatus> getRoomStatuses() {
        return List.of(RoomStatus.values());
    }

    public boolean updateRoomStatus(String hotelName, String roomNumber, RoomStatus status) {
        Hotel hotel = hotelDAO.getHotelByName(hotelName);
        if (hotel == null) {
            return false;
        }

        Room room = roomDAO.getRoomByNumberAndHotel(roomNumber, hotel.getId());
        if (room == null) {
            return false;
        }

        room.setStatus(status);
        return roomDAO.updateRoom(room);
    }
}
