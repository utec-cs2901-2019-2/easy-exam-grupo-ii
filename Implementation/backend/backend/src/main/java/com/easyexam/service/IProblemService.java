package com.easyexam.service;

import com.easyexam.model.*;
import com.easyexam.repository.IProblemRepo;
import com.easyexam.repository.IProblemSubmittedRepo;
import com.easyexam.service.IProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface IProblemService {
    
    List<Problem> getAllProblems();

    List<ProblemSubmitted> findUserProblem(int id);

}
