package com.easyexam.model;

import javax.persistence.*;

@Entity
@Table(name="problem_selected")
public class ProblemSelected {

    @EmbeddedId
    private ProblemSelectedId problemSelectedId;

    public ProblemSelected() { }
    public ProblemSelected(ProblemSelectedId problemSelectedId) {
        this.problemSelectedId = problemSelectedId;
    }

    public ProblemSelectedId getProblemSelectedId() {
        return problemSelectedId;
    }

    public void setProblemSelectedId(ProblemSelectedId problemSelectedId) {
        this.problemSelectedId = problemSelectedId;
    }
}
