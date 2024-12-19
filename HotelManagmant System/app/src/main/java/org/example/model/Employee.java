package org.example.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.example.enums.EmployeeRole;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeID")
    private long Id;

    @ManyToOne
    @JoinColumn(name = "hotelID", referencedColumnName = "hotelID")
    private Hotel hotel;  

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user; 

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private EmployeeRole role;

    @Column(name = "startDate",nullable = false)
    private LocalDate startDate;

    @Column(name = "salary",nullable = false)
    private double salary;

    public Employee() {
    }
    
    public Employee(Hotel hotel, User user, EmployeeRole role, LocalDate startDate, double salary) {
        this.hotel = hotel;
        this.user = user;
        this.role = role;
        this.startDate = startDate;
        this.salary = salary;
    }

    // Getters and Setters
    public long getId() {
        return this.Id;
    }

    public void setEId(long employeeID) {
        this.Id = employeeID;
    }

    public Hotel getHotel() {
        return this.hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EmployeeRole getRole() {
        return this.role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
