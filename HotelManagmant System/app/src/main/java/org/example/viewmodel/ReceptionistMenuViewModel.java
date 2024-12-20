package org.example.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Booking;
import org.example.model.Hotel;
import org.example.model.Receptionist;
import org.example.service.BookingService;
import org.example.service.EmployeeService;
import org.example.service.HousekeepingService;
import org.example.service.ReceptionistService;

import java.util.List;

public class ReceptionistMenuViewModel {
    private final EmployeeService employeeService;
    private final ReceptionistService receptionistService;
    private final BookingService bookingService;
    private final HousekeepingService housekeepingService; 
    private final Hotel hotel;
    private Receptionist currentReceptionist;

    // Списки, которые могут потребоваться
    private final ObservableList<Booking> allBookings;
    //private final ObservableList<Housekeeper> allHousekeepers;

    public ReceptionistMenuViewModel(Long receptionistId) {
        this.receptionistService = new ReceptionistService();
        this.bookingService = new BookingService();
        this.housekeepingService = new HousekeepingService();
        this.employeeService = new EmployeeService();

        this.currentReceptionist = receptionistService.getReceptionistById(receptionistId);
        this.hotel = this.employeeService.getHotelForEmployee(receptionistId);

        this.allBookings = FXCollections.observableArrayList();
        //this.allHousekeepers = FXCollections.observableArrayList();
    }

    public Receptionist getCurrentReceptionist() {
        return currentReceptionist;
    }

    // Метод для получения всех бронирований
    // В отличие от гостя, ресепшионист видит все бронирования.
    public ObservableList<Booking> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        allBookings.clear();
        allBookings.addAll(bookings);
        return allBookings;
    }

    // Метод для отображения всех хаускиперов
    // public ObservableList<Housekeeper> getAllHousekeepers() {
    //     List<Housekeeper> housekeepers = housekeepingService.getAllHousekeepers();
    //     allHousekeepers.clear();
    //     allHousekeepers.addAll(housekeepers);
    //     return allHousekeepers;
    // }

    // Add New Booking — логика подготовки данных для нового бронирования может быть тут
    public void prepareAddNewBooking() {
        // Здесь можно загрузить списки отелей, комнат, если нужно.
    }

    // Assign Housekeeping Task — логика получения списка хаускиперов, их статуса и т.д.
    // public void prepareAssignHousekeepingTask() {
    //     // Здесь можно обновить список доступных хаускиперов или что-то ещё.
    //     getAllHousekeepers();
    // }

    // // View Housekeepers Records — обновляем список, чтобы View мог его отобразить
    // public void prepareViewAllHousekeepersRecords() {
    //     getAllHousekeepers();
    // }

    public Hotel getHotel() {
        return hotel;
    }
}
