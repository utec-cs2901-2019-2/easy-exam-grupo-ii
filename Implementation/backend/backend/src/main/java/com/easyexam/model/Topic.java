package com.easyexam.model;

import javax.persistence.*;

@Entity
@Table(name="topics")
public class Topic {

    @Id
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "topicDad_id",nullable = true)
    private Topic topicDad;

    public Topic() { }

    public Topic(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
