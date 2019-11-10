package com.easyexam.service;

import com.easyexam.model.Problem;
import com.easyexam.model.aux.ProblemCompleted;

import java.util.List;

public interface IProblemService {

    Boolean save(ProblemCompleted p);

    List<Problem> getAllProblems();

    List<Problem> getAllProblemsByUsers(int idUser);
}
