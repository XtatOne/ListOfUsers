package com.xtatone.listOfUsers.entity;

import javax.persistence.*;

@Entity
@Table
public class Users {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private  String surname;

    @Column(name = "department")
    private String department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;

    @Column(name = "salary")
    private Integer salary;

    public Users() {
    }

    public Users(int id, String name, String surname, String department, UserDetails userDetails, Integer salary) {

        this.id             = id;
        this.name           = name;
        this.surname        = surname;
        this.department     = department;
        this.userDetails    = userDetails;
        this.salary         = salary;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
