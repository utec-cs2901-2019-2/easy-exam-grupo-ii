package com.easyexam.model;

import javax.persistence.*;

@Entity
@Table(name="alternative_solution")
@SequenceGenerator(name="seq_alt",sequenceName = "seqAlt")
public class AlternativeSolution {

    @Id
    @GeneratedValue(generator = "seq_alt")
    private int id;

    @Column(name="body")
    private String body;

    @Column(name="value")
    private Boolean value;

    @ManyToOne
    @JoinColumn(name = "solution_id",nullable = false)
    private SolutionProblem solutionProblem;

    public AlternativeSolution() { }

    public AlternativeSolution(String body, Boolean value, SolutionProblem solutionProblem) {
        this.body = body;
        this.value = value;
        this.solutionProblem = solutionProblem;
    }

    public SolutionProblem getSolutionProblem() {
        return solutionProblem;
    }

    public void setSolutionProblem(SolutionProblem solutionProblem) {
        this.solutionProblem = solutionProblem;
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

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
