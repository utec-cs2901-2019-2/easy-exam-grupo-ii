package com.easyexam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="teacher")
public class Teacher {

    @Id
    private int id;

    @Column(name="first_name")
    private String firstname;

    @Column(name="last_name")
    private String lastname;

    @Column(name="institution")
    private String institution;

    @Column(name="country")
    private String country;

    @Column(name="date_birth")
    private Date date_birth;

    @Column(name="gender")
    private String gender;

    @Column(name="bonus")
    private int bonus;

    @Column(name="defaultTemplate")
    private int defaultTemplate;

    @Column(name="alerts")
    private int alerts;

    @OneToOne
    @MapsId
    private User user;

    public Teacher() { }

    public Teacher(String firstName, String lastName, String country, Date date_birth) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.country = country;
        this.date_birth = date_birth;
    }

    public Teacher(String firstname, String lastname, String institution, String country, Date date_birth, String gender, int bonus, int defaultTemplate, int alerts) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.institution = institution;
        this.country = country;
        this.date_birth = date_birth;
        this.gender = gender;
        this.bonus = bonus;
        this.defaultTemplate = defaultTemplate;
        this.alerts = alerts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getDefaultTemplate() {
        return defaultTemplate;
    }

    public void setDefaultTemplate(int defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }

    public int getAlerts() {
        return alerts;
    }

    public void setAlerts(int alerts) {
        this.alerts = alerts;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
