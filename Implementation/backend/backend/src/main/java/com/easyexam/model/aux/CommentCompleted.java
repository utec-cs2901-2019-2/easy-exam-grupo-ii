package com.easyexam.model.aux;

public class CommentCompleted {
    private int idTeacher;
    private int idProblem;
    private String description;

    public CommentCompleted() {}
    public CommentCompleted(int idTeacher, int idProblem, String description) {
        this.idTeacher = idTeacher;
        this.idProblem = idProblem;
        this.description = description;
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
