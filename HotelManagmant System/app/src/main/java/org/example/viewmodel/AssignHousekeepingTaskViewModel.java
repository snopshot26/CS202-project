// AssignHousekeepingTaskViewModel.java
package org.example.viewmodel;

import org.example.model.Housekeeping;
import org.example.model.*;
import org.example.service.*;
import org.example.enums.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AssignHousekeepingTaskViewModel {
    
    private final HousekeepingService housekeepingService;
    private final RoomService roomService;
    private final EmployeeService employeeService;
    private final HotelService hotelService;
    private final Hotel hotel;

    public AssignHousekeepingTaskViewModel(long receptionistId) {
        this.housekeepingService = new HousekeepingService();
        this.roomService = new RoomService();
        this.employeeService = new EmployeeService();
        this.hotelService = new HotelService();
        this.hotel = employeeService.getHotelForEmployee(receptionistId);
    }


    public List<Housekeeping> getAllHousekeepers() {
        return housekeepingService.getAllHousekeepers();
    }

    public List<Room> getRoomsByHotel() {
        return roomService.getRoomsByHotelId(hotel.getId());
    }

    public List<Housekeeping> getHousekeepersByHotel() {
        return employeeService.getHousekeepingByHotelId(hotel.getId());
    }

    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }



    public boolean assignTask(Long housekeeperId, String taskDescription, LocalDate taskDate, Room room) {
        // Validate inputs
        if (housekeeperId == null || taskDescription == null || taskDescription.isEmpty() || taskDate == null || room == null) {
            return false;
        }

        return housekeepingService.assignTask(housekeeperId, taskDescription, taskDate, room);
    }
}
