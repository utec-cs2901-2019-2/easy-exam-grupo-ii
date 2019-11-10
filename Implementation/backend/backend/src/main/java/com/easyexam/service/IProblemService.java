package com.easyexam.service;

import com.easyexam.model.Problem;
<<<<<<< HEAD
import com.easyexam.repository.IProblemRepo;
import com.easyexam.service.IProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface IProblemService {
    
    @Autowired
    private IProblemRepo problemRepo;

    List<Problem> getAllProblems();

    String jsonProblems();
=======
import com.easyexam.model.aux.ProblemCompleted;

import java.util.List;

public interface IProblemService {

    Boolean save(ProblemCompleted p);

    List<Problem> getAllProblems();

    List<Problem> getAllProblemsByUsers(int idUser);
>>>>>>> origin/develop
}
