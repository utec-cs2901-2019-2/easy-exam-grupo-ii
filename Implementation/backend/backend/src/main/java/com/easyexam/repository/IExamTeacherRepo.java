package com.easyexam.repository;

import com.easyexam.model.ExamTeacher;
import com.easyexam.model.ExamTeacherId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExamTeacherRepo extends JpaRepository<ExamTeacher, ExamTeacherId> {

    @Override
    List<ExamTeacher> findAll();

    List<ExamTeacher> findAllByExamTeacherId_IdTeacher(int idTeacher);

}
