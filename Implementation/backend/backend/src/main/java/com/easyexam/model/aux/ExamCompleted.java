package com.easyexam.model.aux;

import java.util.Date;
import java.util.List;

public class ExamCompleted {

    private int idTeacher;

    private String title;

    //private Date creationDate;

    private List<ProblemCompleted> listProblems;


    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProblemCompleted> getListProblems() {
        return listProblems;
    }

    public void setListProblems(List<ProblemCompleted> listProblems) {
        this.listProblems = listProblems;
    }
}
