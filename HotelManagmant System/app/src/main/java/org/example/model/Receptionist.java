package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.example.enums.WorkShifTime;

@Entity
public class Receptionist extends User {
    
    @Column(name ="workShift", nullable = false)
    private WorkShifTime workShift;

    public WorkShifTime getWorkShift() {
        return this.workShift;
    }

    public void setWorkShift(WorkShifTime workShift) {
        this.workShift = workShift;
    }

}
