package com.easyexam.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="exam")
public class Exam {

    @Id
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="creation_date")
    private Date creationDate;

    public Exam() { }

    public Exam(String title, Date creationDate) {
        this.title = title;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
