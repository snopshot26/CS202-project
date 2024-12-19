package org.example.service;

import java.util.List;

import org.example.dao.HousekeepingDAO;
import org.example.model.Housekeeping;

public class HousekeepingService {
    private final HousekeepingDAO HousekeepingDAO = new HousekeepingDAO();

    public Housekeeping getHousekeeping(long HousekeepingId){
        return this.HousekeepingDAO.getHousekeepingById(HousekeepingId);
    }

    public List<Housekeeping> getAllHousekeepings(){
        return this.HousekeepingDAO.getAllHousekeepings();
    }

    public void assignTask(HousekeepingTask task) {
        this.housekeepingDAO.addTask(task);
    }

    public List<HousekeepingTask> getAllTasks() {
        return this.housekeepingDAO.getAllTasks();
    }

}

}
