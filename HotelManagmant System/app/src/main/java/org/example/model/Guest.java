package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.example.enums.UserType;

@Entity
@Table(name = "Guest")
@PrimaryKeyJoinColumn(name = "userID")

public class Guest extends User { 

    @Column(name ="loyaltyPoints",nullable = false)
    private int loyaltyPoints = 0;

    public Guest() {
    }

    public Guest(String name, String email, String phoneNumber,String password, UserType userType, int loyaltyPoints) {
        super(name, email, phoneNumber, userType, password);
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getLoyaltyPoints() {
        return this.loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}

