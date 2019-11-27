package com.easyexam.model.aux;

public class TeacherProblem {

    private int idProblem;
    private int idTeacher;

    public TeacherProblem() {
    }
    public TeacherProblem(int idProblem, int idTeacher) {
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
