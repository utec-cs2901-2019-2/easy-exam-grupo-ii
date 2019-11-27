package com.easyexam.model.aux;

public class CommentCompleted {
    private int idTeacher;
    private String nameTeacher;
    private int idProblem;
    private String description;

    public CommentCompleted() {}

    public CommentCompleted(int idTeacher, String nameTeacher, int idProblem, String description) {
        this.idTeacher = idTeacher;
        this.nameTeacher = nameTeacher;
        this.idProblem = idProblem;
        this.description = description;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
