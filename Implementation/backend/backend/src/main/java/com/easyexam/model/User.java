package com.easyexam.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Inheritance
@Table(name = "user_base", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Teacher teacher;

    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Admin admin;
    

    public User() { }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.activate = true;
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

    public void setTeacher(Teacher teacher) {
        teacher.setUser(this);
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setAdmin(Admin admin) {
        admin.setUser(this);
        this.admin = admin;
    }

    public Admin getAdmin() {
        return this.admin;
    }
}
