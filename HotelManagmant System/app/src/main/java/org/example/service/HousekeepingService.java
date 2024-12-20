package org.example.service;

import org.example.dao.HousekeepingDAO;
import org.example.dao.HousekeepingTaskDAO;
import org.example.model.Housekeeping;
import org.example.model.*;

import java.time.LocalDate;
import java.util.List;

import org.example.enums.TaskStatus;

public class HousekeepingService {
    private final HousekeepingDAO housekeepingDAO;
    private final HousekeepingTaskDAO housekeepingTaskDAO;

    public HousekeepingService() {
        this.housekeepingDAO = new HousekeepingDAO();
        this.housekeepingTaskDAO = new HousekeepingTaskDAO();
    }

    // Получение всех горничных
    public List<Housekeeping> getAllHousekeepers() {
        return housekeepingDAO.getAllHousekeepings();
    }

    // Получение доступных горничных на определенную дату
    public List<Housekeeping> getAvailableHousekeepers(LocalDate date) {
        return housekeepingDAO.getAvailableHousekeepers(date);
    }

    // Назначение задачи горничной
    public boolean assignTask(Long housekeeperId, String taskDescription, LocalDate taskDate, Room room) {
        Housekeeping housekeeper = housekeepingDAO.getHousekeepingById(housekeeperId);
        if (housekeeper == null) {
            return false;
        }
        HousekeepingTask task = new HousekeepingTask(housekeeper, taskDescription, taskDate,TaskStatus.PENDING,room);
        try {
            housekeepingTaskDAO.addTask(task);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Получение задач горничной
    public List<HousekeepingTask> getHousekeeperTasks(Long housekeeperId) {
        return housekeepingTaskDAO.getTasksByHousekeeperId(housekeeperId);
    }

    public List<HousekeepingTask> getAllTasks() {
        return housekeepingTaskDAO.getAllTasks();
    }


    public List<HousekeepingTask> getPendingTasks(long housekeeperId) {
        return housekeepingTaskDAO.getPendingTasks(housekeeperId);
    }

 
    public List<HousekeepingTask> getCompletedTasks(long housekeeperId) {
        return housekeepingTaskDAO.getCompletedTasks(housekeeperId);
    }

    public boolean completeTask(long taskId) {
        return housekeepingTaskDAO.updateTaskStatusToCompleted(taskId);
    }

    public List<HousekeepingTask> getCleaningSchedule(long housekeeperId) {
        return housekeepingTaskDAO.getCleaningSchedule(housekeeperId);
    }

    public Housekeeping getHousekeeper(long housekeeperId) {
        return housekeepingDAO.getHousekeepingById(housekeeperId);
    }
}
