package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "hotelID")
    private long id;

    @Column(name = "hotelName", nullable=false, length=100)
    private String name;
    
    @Column(name = "address", nullable=false, length = 255)
    private String address;

    public Hotel() {
    }

    public Hotel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long Id){
        this.id = Id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String Name){
        this.name = Name;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String Address){
        this.address = Address;
    }
}
