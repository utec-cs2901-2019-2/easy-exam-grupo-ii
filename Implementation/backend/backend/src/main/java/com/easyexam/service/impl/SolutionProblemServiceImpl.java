package com.easyexam.service.impl;

import com.easyexam.repository.ISolutionProblemRepo;
import com.easyexam.service.ISolutionProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionProblemServiceImpl implements ISolutionProblemService {

    @Autowired
    ISolutionProblemRepo solutionProblemRepo;

}
