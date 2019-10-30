package com.easyexam.model;

import javax.persistence.*;

@Entity
@Table (name = "topic")
@SequenceGenerator(name="seq_usu", sequenceName="seq_usu")
public class Topic {

    //TODO implementation topic relation
    @Id
    @GeneratedValue(generator="seq_usu")
    private int id;

    @Column(name="description")
    private String description;

    //private Topic topicDad;


    public Topic() { }

    public Topic(int id, String description /*, Topic topicDad*/) {
        this.id = id;
        this.description = description;
        //this.topicDad = topicDad;
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

    /*

    public Topic getTopicDad() {
        return topicDad;
    }

    public void setTopicDad(Topic topicDad) {
        this.topicDad = topicDad;
    }
    */
}
