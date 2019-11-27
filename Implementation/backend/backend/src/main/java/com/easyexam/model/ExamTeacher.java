package com.easyexam.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="exam_teacher")
public class ExamTeacher {

    @EmbeddedId
    private ExamTeacherId examTeacherId;

    public ExamTeacher() { }

    public ExamTeacher(ExamTeacherId examTeacherId) {
        this.examTeacherId = examTeacherId;
    }

    public ExamTeacherId getExamTeacherId() {
        return examTeacherId;
    }

    public void setExamTeacherId(ExamTeacherId examTeacherId) {
        this.examTeacherId = examTeacherId;
    }
}
