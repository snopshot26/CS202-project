package org.example.viewmodel;

import org.example.model.Booking;
import org.example.model.Receptionist;
import org.example.model.Room;
import org.example.model.HousekeepingTask;
import org.example.service.BookingService;
import org.example.service.ReceptionistService;
import org.example.service.RoomService;
import org.example.service.HousekeepingService;

import java.util.List;

public class AdministratorMenuViewModel {
    private final RoomService roomService = new RoomService();
    private final ReceptionistService receptionistService = new ReceptionistService();
    private final BookingService bookingService = new BookingService();
    private final HousekeepingService housekeepingService = new HousekeepingService();

    /**
     * Получить список всех комнат.
     *
     * @return список комнат.
     */
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    /**
     * Добавить новую комнату.
     *
     * @param room номер комнаты.
     */
    public void addRoom(Room room) {
        roomService.addRoom(room);
    }

    /**
     * Удалить комнату по ID.
     *
     * @param roomId ID комнаты.
     */
    public void deleteRoom(long roomId) {
        roomService.deleteRoom(roomId);
    }

    /**
     * Обновить статус комнаты.
     *
     * @param roomId ID комнаты.
     * @param status новый статус.
     */
    public void updateRoomStatus(long roomId, String status) {
        Room room = roomService.getRoomById(roomId);
        if (room != null) {
            room.setStatus(status);
            roomService.updateRoom(room);
        }
    }

    /**
     * Получить список всех пользователей (рецепционистов).
     *
     * @return список пользователей.
     */
    public List<Receptionist> getAllReceptionists() {
        return receptionistService.getAllReceptionists();
    }

    /**
     * Добавить нового рецепциониста.
     *
     * @param receptionist рецепционист.
     */
    public void addReceptionist(Receptionist receptionist) {
        receptionistService.addReceptionist(receptionist);
    }

    /**
     * Удалить рецепциониста по ID.
     *
     * @param receptionistId ID рецепциониста.
     */
    public void deleteReceptionist(long receptionistId) {
        receptionistService.deleteReceptionist(receptionistId);
    }

    /**
     * Получить список всех бронирований.
     *
     * @return список бронирований.
     */
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    /**
     * Получить список всех задач уборки.
     *
     * @return список задач уборки.
     */
    public List<HousekeepingTask> getAllHousekeepingTasks() {
        return housekeepingService.getAllTasks();
    }

    /**
     * Сгенерировать отчет по бронированиям.
     *
     * @return строка с отчетом.
     */
    public String generateBookingReport() {
        List<Booking> bookings = bookingService.getAllBookings();
        StringBuilder report = new StringBuilder("Booking Report:\n");
        bookings.forEach(booking -> report.append(booking.toString()).append("\n"));
        return report.toString();
    }

    /**
     * Сгенерировать отчет по задачам уборки.
     *
     * @return строка с отчетом.
     */
    public String generateHousekeepingReport() {
        List<HousekeepingTask> tasks = housekeepingService.getAllTasks();
        StringBuilder report = new StringBuilder("Housekeeping Tasks Report:\n");
        tasks.forEach(task -> report.append(task.toString()).append("\n"));
        return report.toString();
    }
}
