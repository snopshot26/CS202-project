package org.example.model;

import javax.persistence.*;

import org.example.enums.TaskStatus;

import java.time.LocalDate;

@Entity
@Table(name = "HousekeepingTask")
public class HousekeepingTask {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "housekeeperID", nullable = false)
    private Housekeeping housekeeper;
    
    @Column(name = "taskDescription", nullable = false, length = 255)
    private String taskDescription;
    
    @ManyToOne
    @JoinColumn(name = "roomID", referencedColumnName = "roomID")
    private Room room;

    @Column(name = "taskDate", nullable = false)
    private LocalDate taskDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private TaskStatus status;
    
    // Конструкторы
    public HousekeepingTask() {}

    public HousekeepingTask(Housekeeping housekeeper, String taskDescription, LocalDate taskDate, TaskStatus status, Room room) {
        this.housekeeper = housekeeper;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
        this.status = status;
        this.room = room;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return this.id;
    }

    public Housekeeping getHousekeeper() {
        return housekeeper;
    }

    public void setHousekeeper(Housekeeping housekeeper) {
        this.housekeeper = housekeeper;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDate getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(LocalDate taskDate) {
        this.taskDate = taskDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
