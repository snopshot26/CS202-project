package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "hotelID")
    private long Id;

    @Column(name = "hotelName", nullable=false, length=100)
    private String Name;
    
    @Column(name = "address", nullable=false, length = 255)
    private String address;

    public long getId(){
        return this.Id;
    }

    public void setId(long Id){
        this.Id = Id;
    }

    public String getName(){
        return this.Name;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String Address){
        this.address = Address;
    }
}
