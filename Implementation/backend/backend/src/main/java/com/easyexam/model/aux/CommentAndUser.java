package com.easyexam.model.aux;

public class CommentAndUser {

    private int idProblem;
    private int idTeacher;
    private String nameTeacher;
    private String descriptionComment;

    public CommentAndUser() {
    }
    public CommentAndUser(int idProblem, int idTeacher, String nameTeacher, String descriptionComment) {
        this.idProblem = idProblem;
        this.idTeacher = idTeacher;
        this.nameTeacher = nameTeacher;
        this.descriptionComment = descriptionComment;
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

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public String getDescriptionComment() {
        return descriptionComment;
    }

    public void setDescriptionComment(String descriptionComment) {
        this.descriptionComment = descriptionComment;
    }
}
