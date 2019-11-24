package com.easyexam.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ExamProblemId implements Serializable {

    int idProblem;
    int idExam;

    public ExamProblemId() {}

    public ExamProblemId(int idProblem, int idExam) {
        this.idProblem = idProblem;
        this.idExam = idExam;
    }

    public int getIdProblem() {
        return idProblem;
    }

    public void setIdProblem(int idProblem) {
        this.idProblem = idProblem;
    }

    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }
}
