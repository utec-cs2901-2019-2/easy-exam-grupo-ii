package com.easyexam.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CommentId implements Serializable {

    //No son foraneas, pero deberian
    int idTeacher;
    int idProblem;

    public CommentId() {}

    public CommentId(int idTeacher, int idProblem) {
        this.idTeacher = idTeacher;
        this.idProblem = idProblem;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public int getIdProblem() {
        return idProblem;
    }

    public void setIdProblem(int idProblem) {
        this.idProblem = idProblem;
    }
}
