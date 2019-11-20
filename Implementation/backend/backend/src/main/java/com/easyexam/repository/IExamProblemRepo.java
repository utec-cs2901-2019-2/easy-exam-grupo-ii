package com.easyexam.repository;

import com.easyexam.model.ExamProblem;
import com.easyexam.model.ExamProblemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExamProblemRepo extends JpaRepository<ExamProblem, ExamProblemId> {

    @Override
    List<ExamProblem> findAll();

    List<ExamProblem> findAllByExamProblemId_IdExam(int idExam);
}
