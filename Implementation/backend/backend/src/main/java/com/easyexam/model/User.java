package com.easyexam.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "user_role")
@SequenceGenerator(name="seq_usu", sequenceName="seq_usu")  
public class User {

    @Id
    @GeneratedValue(generator="seq_usu")
    private int id;

    @Email
    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="activate")
    private Boolean activate;

    public User() { }

    public User(String email, String password, Boolean activate) {
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
