package com.easyexam.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topic {

    @Id
    private int id;

    private String description;

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
