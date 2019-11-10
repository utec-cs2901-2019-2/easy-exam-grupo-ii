package com.easyexam.model;

import javax.persistence.*;

@Entity
@Table(name="topics")
@SequenceGenerator(sequenceName = "seqTopic",name="seq_topic")
public class Topic {

    //TODO implementation topic relation
    @Id
    @GeneratedValue(generator = "seq_topic")
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "topicDad_id",nullable = false)
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
