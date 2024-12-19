package org.example.service;

import java.util.List;

import org.example.dao.RoomDAO;
import org.example.model.Room;

public class RoomService {
    private final RoomDAO roomDAO = new RoomDAO();

    public Room getRoom(long roomId){
        return this.roomDAO.getRoomById(roomId);
    }

    public List<Room> getAllRooms(){
        return this.roomDAO.getAllRooms();
    }

    public void addRoom(Room room) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addRoom(Room room) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteRoom(long roomId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Room getRoomById(long roomId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateRoom(Room room) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
