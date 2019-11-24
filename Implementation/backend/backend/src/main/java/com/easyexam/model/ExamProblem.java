package com.easyexam.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="exam_problem")
public class ExamProblem {

    @EmbeddedId
    private ExamProblemId examProblemId;

    @Column(name="score")
    private int score;

    public ExamProblem() { }

    public ExamProblem(ExamProblemId examProblemId, int score) {
        this.examProblemId = examProblemId;
        this.score = score;
    }

    public ExamProblemId getExamProblemId() {
        return examProblemId;
    }

    public void setExamProblemId(ExamProblemId examProblemId) {
        this.examProblemId = examProblemId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
