package org.example.model;

import javax.persistence.*;

@Entity
public class Guest extends User { 

    @Column(name ="loyaltyPoints",nullable = false)
    private int loyaltyPoints = 0;

    public int getLoyaltyPoints() {
        return this.loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}

