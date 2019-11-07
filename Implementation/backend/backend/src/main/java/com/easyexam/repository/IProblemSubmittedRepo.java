package com.easyexam.repository;

import com.easyexam.model.ProblemSubmitted;
import com.easyexam.model.ProblemSubmittedId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProblemSubmittedRepo extends JpaRepository<ProblemSubmitted, ProblemSubmittedId> {

    @Override
    List<ProblemSubmitted> findAll();

}
