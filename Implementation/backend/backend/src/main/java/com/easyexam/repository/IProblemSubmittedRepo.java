package com.easyexam.repository;

import com.easyexam.model.ProblemSubmitted;
import com.easyexam.model.ProblemSubmittedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProblemSubmittedRepo extends JpaRepository<ProblemSubmitted, ProblemSubmittedId> {

    @Override
    List<ProblemSubmitted> findAll();

}
