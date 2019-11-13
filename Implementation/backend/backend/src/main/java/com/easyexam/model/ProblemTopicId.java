package com.easyexam.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;


@SqlResultSetMapping (
    name="ProblemTopicProblem",
    classes = {
        @ConstructorResult(
            targetClass = com.easyexam.model.ProblemTopicId.class,
            columns = {
                @ColumnResult(name = "idProblem"),
                @ColumnResult(name = "idTopic")
            }
        )
    }
)
@NamedNativeQuery (
    name = "ProblemTopicQ", 
    query = "select problem.id as idProblem, topics.id as idTopic from problem, topics, problem_topic where problem_topic.id_problem = problem.id and id_topic = topics.id and problem.id = ?",
    resultSetMapping = "ProblemTopicProblem"
)
@Embeddable
public class ProblemTopicId implements Serializable {

    int idProblem;
    int idTopic;

    public ProblemTopicId() {}

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
