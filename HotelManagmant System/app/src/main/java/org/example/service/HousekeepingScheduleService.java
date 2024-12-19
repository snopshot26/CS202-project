package org.example.service;

import java.util.List;

import org.example.dao.HousekeepingScheduleDAO;
import org.example.model.HousekeepingSchedule;

public class HousekeepingScheduleService {
    private final HousekeepingScheduleDAO HousekeepingScheduleDAO = new HousekeepingScheduleDAO();

    public HousekeepingSchedule getHousekeepingSchedule(long HousekeepingScheduleId){
        return this.HousekeepingScheduleDAO.getHousekeepingScheduleById(HousekeepingScheduleId);
    }

    public List<HousekeepingSchedule> getAllHousekeepingSchedules(){
        return this.HousekeepingScheduleDAO.getAllHousekeepingSchedules();
    }
}
