package com.easyexam.service.impl;

import com.easyexam.repository.IProblemRepo;
import com.easyexam.repository.IProblemSubmittedRepo;
import com.easyexam.service.IProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.File;

import java.util.*;

@Service
public class ProblemServiceImpl implements IProblemService {

    @Autowired
    IProblemRepo problemRepo;

    @Autowired
    IProblemSubmittedRepo problemSubmittedRepo;

    @Override
    public List<Problem> getAllProblems(){
        return problemRepo.findAll();
    }

    @Override
    public String jsonProblems(){
        return problemRepo.findCustom();
    }

}
