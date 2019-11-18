package com.easyexam.repository;

import com.easyexam.model.TeacherScore;
import com.easyexam.model.TeacherScoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherScoreRepo extends JpaRepository<TeacherScore, TeacherScoreId> {

    TeacherScore findTeacherScoreByTeacherScoreIdIdProblemAndTeacherScoreId_IdTeacher(int idProblem,int idTeacher);

}
