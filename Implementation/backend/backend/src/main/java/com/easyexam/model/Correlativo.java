package com.easyexam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "correlative")
public class Correlativo {

    @Id
    private int id;

    @Column(name = "id_problem")
    private int idProblem;

    @Column(name = "id_user")
    private int idUser;

    @Column(name = "id_exam")
    private int idExam;

    public Correlativo() { }

    public Correlativo(int idProblem, int idUser, int idExam) {
        this.idProblem = idProblem;
        this.idUser = idUser;
        this.idExam = idExam;
    }

    public int getIdProblem() {
        return idProblem;
    }

    public void setIdProblem(int idProblem) {
        this.idProblem = idProblem;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }
}
