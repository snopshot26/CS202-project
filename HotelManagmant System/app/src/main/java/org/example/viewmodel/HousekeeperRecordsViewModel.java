package org.example.viewmodel;

import org.example.model.HousekeepingTask;
import org.example.service.HousekeepingService;

import java.util.List;

public class HousekeeperRecordsViewModel {
    private final HousekeepingService housekeepingService;
    private final long housekeeperId;

    public HousekeeperRecordsViewModel(long housekeeperId) {
        this.housekeepingService = new HousekeepingService();
        this.housekeeperId = housekeeperId;
    }

    public HousekeepingService getHousekeepingService() {
        return housekeepingService;
    }

    public List<HousekeepingTask> getTasksForHousekeeper(Long housekeeperId) {
        return housekeepingService.getHousekeeperTasks(housekeeperId);
    }
}
