package com.easyexam.model;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    private int id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="phone")
    private Integer phone;

    @Column(name="address")
    private String address;

    @OneToOne
    @MapsId
    private User user;

    public Admin() { }

    public Admin(String fullname, Integer phone, String address) {
        this.fullName = fullname;
        this.phone = phone;
        this.address = address;
    }

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public Integer getPhone(){
        return phone;
    }

    public void setPhone(Integer phone){
        this.phone = phone;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
