package org.example.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Administrator")
@PrimaryKeyJoinColumn(name = "userID")

public class Administrator  extends  User{
    
}
