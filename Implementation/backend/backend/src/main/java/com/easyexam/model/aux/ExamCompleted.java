package com.easyexam.model.aux;

import java.util.List;

public class ExamCompleted {

    private int idTeacher;

    private String title;

    private String indications;

    private String duration;

    private String course;

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

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
