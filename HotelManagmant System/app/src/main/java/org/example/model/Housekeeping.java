package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Housekeeping extends User{
    
    @Column(name="assignedFloors", nullable = false, length=100)
    private String assignedFloors;

    public String getAssignedFloors() {
        return assignedFloors;
    }

    public void setAssignedFloors(String assignedFloors) {
        this.assignedFloors = assignedFloors;
    }
}
