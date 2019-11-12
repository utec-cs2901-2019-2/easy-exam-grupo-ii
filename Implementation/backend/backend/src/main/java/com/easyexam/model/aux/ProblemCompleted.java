package com.easyexam.model.aux;

import com.easyexam.model.Topic;

import java.util.List;

public class ProblemCompleted {

    private int idTeacher;

    private String title;

    private String body;

    private String rutaImage;

    private List<Topic> topics;

    private String descriptionSolution;

    private String pathImageSolution;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRutaImage() {
        return rutaImage;
    }

    public void setRutaImage(String rutaImage) {
        this.rutaImage = rutaImage;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getDescriptionSolution() {
        return descriptionSolution;
    }

    public void setDescriptionSolution(String descriptionSolution) {
        this.descriptionSolution = descriptionSolution;
    }

    public String getPathImageSolution() {
        return pathImageSolution;
    }

    public void setPathImageSolution(String pathImageSolution) {
        this.pathImageSolution = pathImageSolution;
    }
}