package com.easyexam.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Table;
import javax.persistence.SqlResultSetMapping;

@Entity
@Table(name="problem_topic")
public class ProblemTopic {

    @EmbeddedId
    private ProblemTopicId problemTopicId;



    public ProblemTopic() { }
    public ProblemTopic(ProblemTopicId problemTopicId) {
        this.problemTopicId = problemTopicId;
    }

    public ProblemTopicId getProblemTopicId() {
        return problemTopicId;
    }

    public void setProblemTopicId(ProblemTopicId problemTopicId) {
        this.problemTopicId = problemTopicId;
    }
}
