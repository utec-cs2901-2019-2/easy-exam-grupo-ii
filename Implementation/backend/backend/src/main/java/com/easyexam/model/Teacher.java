package com.easyexam.model;

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
    private int country;

    @Column(name="date_birth")
    private Date date_birth;

    @Column(name="gender")
    private int gender;

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

    public Teacher(int id, String firstname, String lastname, String institution, int country, Date date_birth, int gender, int bonus, int defaultTemplate, int alerts) {
        this.id = id;
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

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
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
}
