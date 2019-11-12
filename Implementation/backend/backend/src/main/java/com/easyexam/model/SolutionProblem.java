package com.easyexam.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="solution_problem")
public class SolutionProblem {

    @Id
    private int id;

    @Column(name="description")
    private String description;

    @Column(name="path_image")
    private String pathImage;

    public SolutionProblem() { }

    public SolutionProblem(String description, String pathImage) {
        this.description = description;
        this.pathImage = pathImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }
}
