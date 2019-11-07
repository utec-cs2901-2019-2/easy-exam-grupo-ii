package com.easyexam.repository;

import com.easyexam.model.ExamProblem;
import com.easyexam.model.ExamProblemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IExamProblemRepo extends JpaRepository<ExamProblem, ExamProblemId> {

    @Override
    List<ExamProblem> findAll();

}
