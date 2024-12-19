package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.example.enums.UserType;

@Entity
@Table(name = "Housekeeping")
@PrimaryKeyJoinColumn(name = "userID") // Сопоставление внешнего ключа с родительским
public class Housekeeping extends User{
    
    @Column(name="assignedFloors", nullable = false, length=100)
    private String assignedFloors;

    public Housekeeping() {
    }
    public Housekeeping(String name, String email, String phoneNumber,String password, UserType userType, String assignedFloors) {
        super(name, email, phoneNumber, userType, password);
        this.assignedFloors = assignedFloors;
    }

    public String getAssignedFloors() {
        return assignedFloors;
    }

    public void setAssignedFloors(String assignedFloors) {
        this.assignedFloors = assignedFloors;
    }
}
