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
}
