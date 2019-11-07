package com.easyexam.repository;

import com.easyexam.model.ExamTeacher;
import com.easyexam.model.ExamTeacherId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IExamTeacherRepo extends JpaRepository<ExamTeacher, ExamTeacherId> {

    @Override
    List<ExamTeacher> findAll();

}
