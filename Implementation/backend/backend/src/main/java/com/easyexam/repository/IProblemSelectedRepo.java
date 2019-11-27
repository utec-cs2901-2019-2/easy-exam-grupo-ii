package com.easyexam.repository;

import com.easyexam.model.ProblemSelected;
import com.easyexam.model.ProblemSelectedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProblemSelectedRepo extends JpaRepository<ProblemSelected, ProblemSelectedId> {

//    @Query(value = "select problem_selected.id_problem from problem, problem_selected where problem_selected.id_teacher = ?1", nativeQuery = true)
//    List<ProblemSelected> getSelectedProblems(int idUser);

    List<ProblemSelected> findAllByProblemSelectedId_IdTeacher(int id);

    //omment \/by vodkxa

}
