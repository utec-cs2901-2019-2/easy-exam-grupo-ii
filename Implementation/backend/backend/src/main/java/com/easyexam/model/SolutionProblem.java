package com.easyexam.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="solution_problem")
@SequenceGenerator(name="seq_sol",sequenceName = "seqSol")
public class SolutionProblem {

    @Id
    @GeneratedValue(generator="seq_sol")
    private int id;

    @ManyToOne
    @JoinColumn(name = "problem_id",nullable = false)
    private Problem problem;

    @Column(name="description")
    private String description;

    @Column(name="path_image")
    private String pathImage;

    public SolutionProblem() { }

    public SolutionProblem(Problem problem, String description, String pathImage) {
        this.problem = problem;
        this.description = description;
        this.pathImage = pathImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
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
