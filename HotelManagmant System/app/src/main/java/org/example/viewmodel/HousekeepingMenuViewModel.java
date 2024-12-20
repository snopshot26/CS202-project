// HousekeepingMenuViewModel.java
package org.example.viewmodel;

import org.example.model.HousekeepingTask;

import java.util.List;

import org.example.service.HousekeepingService;

public class HousekeepingMenuViewModel {
    private final HousekeepingService taskService;

    public HousekeepingMenuViewModel() {
        this.taskService = new HousekeepingService();
    }

    public List<HousekeepingTask> getPendingTasks(long housekeeperId) {
        return taskService.getPendingTasks(housekeeperId);
    }

    
    public List<HousekeepingTask> getCompletedTasks(long housekeeperId) {
        return taskService.getCompletedTasks(housekeeperId);
    }

    public boolean completeTask(long taskId) {
        return taskService.completeTask(taskId);
    }

    public List<HousekeepingTask> getCleaningSchedule(long housekeeperId) {
        return taskService.getCleaningSchedule(housekeeperId);
    }
}
