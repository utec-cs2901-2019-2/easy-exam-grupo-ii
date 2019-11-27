package com.easyexam.repository;

import com.easyexam.model.SolutionProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISolutionProblemRepo extends JpaRepository<SolutionProblem,Integer> {

    @Override
    List<SolutionProblem> findAll();

    SolutionProblem findSolutionProblemById(int idProblem);
}
