package org.example.model;

import javax.persistence.*;

import java.time.LocalDate;

import org.example.enums.HousekeepingStatus;

@Entity
public class HousekeepingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskID")
    private long Id;

    @ManyToOne
    @JoinColumn(name = "roomID", referencedColumnName = "roomID")
    private Room room; 

    @ManyToOne
    @JoinColumn(name = "assignedTo", referencedColumnName = "housekeepingID")
    private Housekeeping housekeeping;
    
    @Column(name = "scheduledDate", nullable = false)
    private LocalDate scheduledDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private HousekeepingStatus status;

    public long getId() {
        return this.Id;
    }

    public void setId(long taskID) {
        this.Id = taskID;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Housekeeping getHousekeeping() {
        return this.housekeeping;
    }

    public void setHousekeeping(Housekeeping housekeeping) {
        this.housekeeping = housekeeping;
    }

    public LocalDate getScheduledDate() {
        return this.scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public HousekeepingStatus getStatus() {
        return this.status;
    }

    public void setStatus(HousekeepingStatus status) {
        this.status = status;
    }
}
