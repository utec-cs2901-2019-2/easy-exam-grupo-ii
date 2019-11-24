package com.easyexam.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="audit")
public class Audit {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "admin_id",nullable = false)
    private Admin admin;


    @ManyToOne
    @JoinColumn(name = "teacher_id",nullable = false)
    private Teacher teacher;

    @Column(name="date_action")
    private Date date_action;

    @Column(name="action")
    private String action;

    @Column(name="comments")
    private String comments;

    public Audit() { }

    public Audit(Admin admin, Teacher teacher, Date date_action, String action, String comments) {
        this.admin = admin;
        this.teacher = teacher;
        this.date_action = date_action;
        this.action = action;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getDate_action() {
        return date_action;
    }

    public void setDate_action(Date date_action) {
        this.date_action = date_action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
