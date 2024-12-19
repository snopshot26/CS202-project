package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.example.enums.UserType;
import org.example.enums.WorkShifTime;

@Entity
@Table(name = "Receptionist")
@PrimaryKeyJoinColumn(name = "userID")

public class Receptionist extends User {
    
    @Column(name ="workShift", nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkShifTime workShift;

    public Receptionist() {
    }

    public Receptionist(String name, String email, String phoneNumber,String password, UserType userType, WorkShifTime workShift) {
        super(name, email, phoneNumber, userType, password);
        this.workShift = workShift;
    }


    public WorkShifTime getWorkShift() {
        return this.workShift;
    }

    public void setWorkShift(WorkShifTime workShift) {
        this.workShift = workShift;
    }

}
