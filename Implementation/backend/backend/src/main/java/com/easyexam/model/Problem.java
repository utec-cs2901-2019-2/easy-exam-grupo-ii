package com.easyexam.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name = "Problem")
@SequenceGenerator(name="seq_prob", sequenceName="seq_prob")
public class Problem {

    //TODO implementation
    @Id
    @GeneratedValue(generator="seq_prob")
    private int id;

    @Column(name="title")
    private String title;
    
    @Column(name="description")
    private String description;

    @Column(name="score")
    private float score;

    @Column(name="activate")
    private int qualifiers;

    @Column(name="topic")
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
