package com.easyexam.service;

import com.easyexam.model.*;
import java.util.List;

import com.easyexam.model.Problem;
import com.easyexam.model.aux.ProblemCompleted;
import javassist.compiler.ast.Pair;


public interface IProblemService {

    Boolean save(ProblemCompleted p);

    Problem findProblemById(int id);

    List<Problem> getAllProblems();

    List<ProblemSubmitted> findUserProblem(int idUser);

    List<ProblemCompleted> getProblemTopics();

    Boolean saveProblemSelected(ProblemSelected promSecl);
    
    List<ProblemCompleted> getProblemSelected(int idUser);

    void updateRateProblem(int idProblem,int rate);

    int getTeacherScore(int idProblem,int idTeacher);

    Boolean saveTeacherScore(int idProblem,int idTeacher,int score);

    SolutionProblem getSolutionOfProblem(int idProblem);

    List<ProblemCompleted> getListProblemCompletedSubmitedByUser(int idUser);

    List<Problem> getReportedProblems();

    void delete(int idProblem);

}
