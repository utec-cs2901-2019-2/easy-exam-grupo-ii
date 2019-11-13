package com.easyexam.repository;

import com.easyexam.model.ProblemSelected;
import com.easyexam.model.ProblemSelectedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProblemSelectedRepo extends JpaRepository<ProblemSelected, ProblemSelectedId> {

    @Query (value = "select problem_selected.id_problem from problem, problem_selected where problem_selected.id_teacher = ?1", nativeQuery = true)
    List<ProblemSelected> getSelectedProblems(int idUser);


    //omment \/by vodkxa

}
