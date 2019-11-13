package com.easyexam.repository;

import com.easyexam.model.ProblemSubmitted;
import com.easyexam.model.ProblemSubmittedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

@Repository
public interface IProblemSubmittedRepo extends JpaRepository<ProblemSubmitted, ProblemSubmittedId> {

    @Override
    List<ProblemSubmitted> findAll();
 
    @Query (value = "select users.id, problem.id from users,problem, problem_submitted ps where users.id = ps.id_user and ps.id_problem = problem.id and users.id = ?1", nativeQuery = true)
    List<ProblemSubmitted> findUserQuestions(int id);

}
