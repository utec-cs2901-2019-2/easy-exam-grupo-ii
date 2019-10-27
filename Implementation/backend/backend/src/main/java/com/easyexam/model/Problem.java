package com.easyexam.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Problem {

    @Id
    private int id;

    private String title;

    private String description;

    private float score;

    private int qualifiers;

    private Topic topic;

    public Problem() { }

    public Problem(int id, String title, String description, float score, int qualifiers, Topic topic) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.score = score;
        this.qualifiers = qualifiers;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getQualifiers() {
        return qualifiers;
    }

    public void setQualifiers(int qualifiers) {
        this.qualifiers = qualifiers;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

}
