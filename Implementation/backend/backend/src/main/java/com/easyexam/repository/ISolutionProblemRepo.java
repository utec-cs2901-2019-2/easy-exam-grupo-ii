package com.easyexam.repository;

import com.easyexam.model.SolutionProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISolutionProblemRepo extends JpaRepository<SolutionProblem,Integer> {

    @Override
    List<SolutionProblem> findAll();
}
