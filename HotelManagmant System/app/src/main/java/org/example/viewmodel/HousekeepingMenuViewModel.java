package org.example.viewmodel;

import org.example.model.HousekeepingTask;
import org.example.model.Housekeeper;
import org.example.service.HousekeepingService;

import java.util.List;

public class HousekeepingMenuViewModel {
    private final HousekeepingService housekeepingService = new HousekeepingService();

    /**
     * Получить список всех задач уборки.
     *
     * @return список задач.
     */
    public List<HousekeepingTask> getAllTasks() {
        return housekeepingService.getAllTasks();
    }

    /**
     * Добавить новую задачу уборки.
     *
     * @param roomNumber   номер комнаты.
     * @param description  описание задачи.
     */
    public void assignTask(String roomNumber, String description) {
        HousekeepingTask task = new HousekeepingTask();
        task.setRoomNumber(roomNumber);
        task.setDescription(description);
        task.setCompleted(false);

        housekeepingService.assignTask(task);
    }

    /**
     * Обновить статус задачи на выполнено.
     *
     * @param taskId ID задачи.
     */
    public void markTaskAsCompleted(long taskId) {
        List<HousekeepingTask> tasks = housekeepingService.getAllTasks();
        for (HousekeepingTask task : tasks) {
            if (task.getId() == taskId) {
                task.setCompleted(true);
                housekeepingService.assignTask(task);
                break;
            }
        }
    }

    /**
     * Получить список всех уборщиков и их доступность.
     *
     * @return список уборщиков.
     */
    public List<Housekeeper> getAllHousekeepers() {
        return housekeepingService.getAllHousekeepers();
    }

    /**
     * Проверить доступность уборщика.
     *
     * @param housekeeperName имя уборщика.
     * @return true, если доступен, иначе false.
     */
    public boolean isHousekeeperAvailable(String housekeeperName) {
        List<Housekeeper> housekeepers = housekeepingService.getAllHousekeepers();
        for (Housekeeper housekeeper : housekeepers) {
            if (housekeeper.getName().equals(housekeeperName)) {
                return housekeeper.isAvailable();
            }
        }
        return false;
    }
}
