package org.example.viewmodel;

import org.example.dao.BookingDAO;
import org.example.dao.HotelDAO;
import org.example.dao.RoomDAO;
import org.example.model.Hotel;
import org.example.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class DeleteRoomViewModel {
    private final HotelDAO hotelDAO;
    private final RoomDAO roomDAO;
    private final BookingDAO bookingDAO;
    private final long adminId;

    public DeleteRoomViewModel(long adminId) {
        this.hotelDAO = new HotelDAO();
        this.roomDAO = new RoomDAO();
        this.bookingDAO = new BookingDAO();
        this.adminId = adminId;
    }

    public List<Room> getAllRooms() {
        return roomDAO.getAllRooms();
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

    public boolean deleteRoom(long roomId) {

        // Проверка наличия активных бронирований
        boolean hasActiveBookings = bookingDAO.hasActiveBookings(roomId);
        if (hasActiveBookings) {
            return false; // Невозможно удалить комнату с активными бронированиями
        }

        return roomDAO.deleteRoom(roomId);
    }

    public long getAdminId() {
        return adminId;
    }
}
