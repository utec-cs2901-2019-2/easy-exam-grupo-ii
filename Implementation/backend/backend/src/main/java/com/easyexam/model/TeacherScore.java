package com.easyexam.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teacher_score")
public class TeacherScore {

    @EmbeddedId
    private TeacherScoreId teacherScoreId;

    @Column(name = "score")
    private int score;

    public TeacherScore() {}

    public TeacherScore(TeacherScoreId teacherScoreId, int score) {
        this.teacherScoreId = teacherScoreId;
        this.score = score;
    }

    public TeacherScoreId getTeacherScoreId() {
        return teacherScoreId;
    }

    public void setTeacherScoreId(TeacherScoreId teacherScoreId) {
        this.teacherScoreId = teacherScoreId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
