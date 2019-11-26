package com.easyexam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="admin")
public class Admin {

    @Id
    private int id;

    @Column(name="full_name")
    private String fullname;

    @Column(name="phone")
    private String phone;

    @Column(name="address")
    private String address;

    @OneToOne
    @MapsId
    private User user;

    public Admin() { }

    public Admin(String fullname, String phone, String address) {
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
