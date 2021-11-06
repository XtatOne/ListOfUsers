package com.xtatone.listOfUsers.entity;

import javax.persistence.*;

@Entity
@Table
public class UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "details")
    private String details;

    public UserDetails() {
    }

    public UserDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return getDetails();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
