package com.easyexam.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ExamTeacherId implements Serializable {

    int idTeacher;
    int idExam;

    public ExamTeacherId() {}

    public ExamTeacherId(int idTeacher, int idExam) {
        this.idTeacher = idTeacher;
        this.idExam = idExam;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }
}
