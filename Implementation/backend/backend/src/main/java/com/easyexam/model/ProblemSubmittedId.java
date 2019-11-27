package com.easyexam.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProblemSubmittedId implements Serializable {

    int idUser;
    int idProblem;
    public ProblemSubmittedId() {
    }

    public ProblemSubmittedId(int idUser, int idProblem) {
        this.idUser = idUser;
        this.idProblem = idProblem;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProblem() {
        return idProblem;
    }

    public void setIdProblem(int idProblem) {
        this.idProblem = idProblem;
    }
}
