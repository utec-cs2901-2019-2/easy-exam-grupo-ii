package com.easyexam.model;

import java.util.Date;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "teacher")
public class Teacher {
    
    @Id
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="Country")
    private String country;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    @OneToOne
    @MapsId
    private User user;
    
    public Teacher() { }

    public Teacher(String firstName, String lastName, String country, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.birthday = birthday;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public Date getBirthday(){
        return birthday;
    }

    public void setBirtday(Date birthday){
        this.birthday = birthday;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
