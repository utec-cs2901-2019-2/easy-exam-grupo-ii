package com.easyexam.repository;

import com.easyexam.model.Problem;
import com.easyexam.model.ProblemSelected;
import com.easyexam.model.ProblemSelectedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IProblemSelectedRepo extends JpaRepository<ProblemSelected, ProblemSelectedId> {

    @Query(value = "select problem.id, from users,problem, problem_submitted ps where users.id = ps.id_user and ps.id_problem = problem.id and users.id = ?1", nativeQuery = true)
    List<Problem> findUserQuestions(int idU);
}
