package com.easyexam.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name="problem_submitted")
public class ProblemSubmitted {

    @EmbeddedId
    private ProblemSubmittedId problemSubmittedId;

    @Column(name="date_created")
    private Date dateCreated;

    public ProblemSubmitted() {

    }
    public ProblemSubmitted(ProblemSubmittedId problemSubmittedId, Date dateCreated) {
        this.problemSubmittedId = problemSubmittedId;
        this.dateCreated = dateCreated;
    }

    public ProblemSubmittedId getProblemSubmittedId() {
        return problemSubmittedId;
    }

    public void setProblemSubmittedId(ProblemSubmittedId problemSubmittedId) {
        this.problemSubmittedId = problemSubmittedId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
