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

    public void addSchedule(HousekeepingSchedule schedule) {
        this.housekeepingScheduleDAO.addSchedule(schedule);
    }

    public void modifySchedule(HousekeepingSchedule schedule) {
        this.housekeepingScheduleDAO.updateSchedule(schedule);
    }

    public void deleteSchedule(long scheduleId) {
        this.housekeepingScheduleDAO.deleteSchedule(scheduleId);
    }

    public HousekeepingSchedule getScheduleById(long scheduleId) {
        return this.housekeepingScheduleDAO.getScheduleById(scheduleId);
    }

    public List<HousekeepingSchedule> getAllSchedules() {
        return this.housekeepingScheduleDAO.getAllSchedules();
    }
}


