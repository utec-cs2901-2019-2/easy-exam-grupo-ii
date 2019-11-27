package com.easyexam.model;

import javax.persistence.*;

@Entity
@Table(name="problem")
public class Problem {


    @Id
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name="body",columnDefinition="TEXT")
    private String body;

    @Column(name="rutaImage")
    private String rutaImage;

    @Column(name = "score")
    private float score;

    @Column(name="qualifiers")
    private int qualifiers;

    public Problem() { }

    public Problem(String title, String type, String body, String rutaImage, float score, int qualifiers) {
        this.title = title;
        this.type = type;
        this.body = body;
        this.rutaImage = rutaImage;
        this.score = score;
        this.qualifiers = qualifiers;
    }

    public Problem(String title, String body, String rutaImage, int i, int i1) {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRutaImage() {
        return rutaImage;
    }

    public void setRutaImage(String rutaImage) {
        this.rutaImage = rutaImage;
    }
}
