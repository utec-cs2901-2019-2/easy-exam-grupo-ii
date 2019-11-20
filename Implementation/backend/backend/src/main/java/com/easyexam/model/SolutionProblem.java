package com.easyexam.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="solution_problem")
public class SolutionProblem {

    @Id
    private int id;

    @Column(name="description",columnDefinition="TEXT")
    private String body;

    @Column(name="path_image")
    private String pathImage;

    public SolutionProblem() { }

    public SolutionProblem(int id, String body, String pathImage) {
        this.id = id;
        this.body = body;
        this.pathImage = pathImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }
}
