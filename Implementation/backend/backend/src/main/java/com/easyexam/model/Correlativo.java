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

    @Column(name = "id_topic")
    private int idTopic;

    public Correlativo() { }


    public Correlativo(int id, int idProblem, int idUser, int idExam, int idTopic) {
        this.id = id;
        this.idProblem = idProblem;
        this.idUser = idUser;
        this.idExam = idExam;
        this.idTopic = idTopic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
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
