package com.easyexam.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TeacherScoreId implements Serializable {

    int idProblem;
    int idTeacher;

    public TeacherScoreId() {}

    public TeacherScoreId(int idProblem, int idTeacher) {
        this.idProblem = idProblem;
        this.idTeacher = idTeacher;
    }

    public int getIdProblem() {
        return idProblem;
    }

    public void setIdProblem(int idProblem) {
        this.idProblem = idProblem;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }
}
