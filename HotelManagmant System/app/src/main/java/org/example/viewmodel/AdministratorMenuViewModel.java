package org.example.viewmodel;

import org.example.enums.RoomStatus;
import org.example.model.Receptionist;
import org.example.model.*;
import org.example.model.HousekeepingTask;
import org.example.service.*;

import java.util.List;

public class AdministratorMenuViewModel {
    private final RoomService roomService;
    private final ReceptionistService receptionistService;
    private final HousekeepingService housekeepingService;
    private final EmployeeService employeeService;
    private final long adminId;
    private final Hotel hotel;

    public AdministratorMenuViewModel(long adminId) {
        this.roomService = new RoomService();
        this.receptionistService = new ReceptionistService();
        this.housekeepingService = new HousekeepingService();
        this.employeeService = new EmployeeService();
        this.adminId = adminId;
        this.hotel = this.employeeService.getHotelForEmployee(adminId);
    }


    public boolean deleteRoom(Long roomId) {
        return roomService.deleteRoom(roomId);
    }


    public Room getRoomById(Long roomId) {
        return roomService.getRoom(roomId);
    }


    public boolean updateRoomStatus(Long roomId, String status) {
        RoomStatus roomStatus;
        try {
            roomStatus = RoomStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Некорректный статус
            return false;
        }
        Room room = roomService.getRoom(roomId);
        if (room == null) {
            return false;
        }
        room.setStatus(roomStatus);
        return roomService.updateRoom(room);
    }


    public boolean addReceptionist(Receptionist receptionist) {
        return receptionistService.addReceptionist(receptionist);
    }


    public boolean deleteReceptionist(Long receptionistId) {
        return receptionistService.deleteReceptionist(receptionistId);
    }

    public List<HousekeepingTask> getAllHousekeepingTasks() {
        return housekeepingService.getAllTasks();
    }

    public long getAdminId() {
        return adminId;
    }

}
