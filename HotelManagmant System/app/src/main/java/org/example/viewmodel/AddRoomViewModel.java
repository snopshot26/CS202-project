package org.example.viewmodel;

import org.example.dao.HotelDAO;
import org.example.dao.RoomDAO;
import org.example.enums.RoomStatus;
import org.example.enums.RoomType;
import org.example.model.Hotel;
import org.example.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class AddRoomViewModel {
    private final HotelDAO hotelDAO;
    private final RoomDAO roomDAO;

    public AddRoomViewModel() {
        this.hotelDAO = new HotelDAO();
        this.roomDAO = new RoomDAO();
    }

    public List<String> getHotelNames() {
        List<Hotel> hotels = hotelDAO.getAllHotels();
        return hotels.stream().map(Hotel::getName).collect(Collectors.toList());
    }

    public List<RoomType> getRoomTypes() {
        return List.of(RoomType.values());
    }

    public boolean addRoom(String hotelName, String roomNumber, RoomType roomType, double price) {
        Hotel hotel = hotelDAO.getHotelByName(hotelName);
        if (hotel == null) {
            return false;
        }

        Room existingRoom = roomDAO.getRoomByNumberAndHotel(roomNumber, hotel.getId());
        if (existingRoom != null) {
            return false; // Комната уже существует
        }

        Room room = new Room();
        room.setHotel(hotel);
        room.setRoomNumber(roomNumber);
        room.setRoomType(roomType);
        room.setPrice(price);
        room.setStatus(RoomStatus.AVAILABLE);

        return roomDAO.addRoom(room);
    }
}
