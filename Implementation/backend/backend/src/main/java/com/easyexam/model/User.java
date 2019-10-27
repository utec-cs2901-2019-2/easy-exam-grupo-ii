package com.easyexam.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private int id;

    private String email;

    private String password;

    private Boolean activate;

    public User() { }

    public User(int id, String email, String password, Boolean activate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.activate = activate;
    }

    public int getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }
}
