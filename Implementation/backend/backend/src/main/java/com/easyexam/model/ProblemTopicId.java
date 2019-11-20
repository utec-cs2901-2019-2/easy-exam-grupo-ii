package com.easyexam.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;

@Embeddable
public class ProblemTopicId implements Serializable {

    int idProblem;
    int idTopic;

    public ProblemTopicId() {

    }
    public ProblemTopicId(int idProblem, int idTopic) {
        this.idProblem = idProblem;
        this.idTopic = idTopic;
    }

    public int getIdProblem() {
        return idProblem;
    }

    public void setIdProblem(int idProblem) {
        this.idProblem = idProblem;
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }
}
