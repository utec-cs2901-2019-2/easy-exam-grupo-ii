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

    @Column(name="indications",columnDefinition="TEXT")
    private String indications;

    @Column(name="duration")
    private String duration;

    @Column(name="course")
    private String course;

    public Exam() { }

    public Exam(int id, String title, Date creationDate, String indications, String duration, String course) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.indications = indications;
        this.duration = duration;
        this.course = course;
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



    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
