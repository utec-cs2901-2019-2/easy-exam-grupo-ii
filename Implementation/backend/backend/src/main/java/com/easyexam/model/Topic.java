package com.easyexam.model;

import javax.persistence.*;

@Entity
@Table (name = "topic")
@SequenceGenerator(name="seq_topic", sequenceName="seq_topic")
public class Topic {

    //TODO implementation topci relation
    @Id
    @GeneratedValue(generator="seq_topic")
    private int id;

    @Column(name="description")
    private String description;

    @Column(name="topicDad") 
    private Topic topicDad;


    public Topic() { }

    public Topic(int id, String description, Topic topicDad) {
        this.id = id;
        this.description = description;
        this.topicDad = topicDad;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Topic getTopicDad() {
        return topicDad;
    }

    public void setTopicDad(Topic topicDad) {
        this.topicDad = topicDad;
    }
}
