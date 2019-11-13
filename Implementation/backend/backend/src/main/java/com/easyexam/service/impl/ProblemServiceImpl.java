package com.easyexam.service.impl;

import com.easyexam.model.*;
import com.easyexam.model.aux.ProblemCompleted;
import com.easyexam.repository.IProblemRepo;
import com.easyexam.repository.IProblemSubmittedRepo;
import com.easyexam.repository.IProblemTopicRepo;
import com.easyexam.repository.ISolutionProblemRepo;
import com.easyexam.service.IProblemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.File;

import java.util.*;

@Service
public class ProblemServiceImpl implements IProblemService {

    private static Logger LOG= LoggerFactory.getLogger(ProblemServiceImpl.class);

    @Autowired
    IProblemRepo problemRepo;

    @Autowired
    IProblemSubmittedRepo problemSubmittedRepo;

    @Autowired
    IProblemTopicRepo problemTopicRepo;

    @Autowired
    ISolutionProblemRepo solutionProblemRepo;

    @Override
    public Boolean save(ProblemCompleted p) {

        try{
            Problem problem=new Problem(p.getTitle(),p.getBody(),p.getRutaImage(),"",0,0);
            problem=problemRepo.save(problem);

            ProblemSubmitted problemSubmitted=
                    new ProblemSubmitted(new ProblemSubmittedId(p.getIdTeacher(),problem.getId()),new Date());
            problemSubmittedRepo.save(problemSubmitted);

            for(Topic t:p.getTopics()){
                ProblemTopic problemTopic=new ProblemTopic(new ProblemTopicId(problem.getId(),t.getId()));
                problemTopicRepo.save((problemTopic));
            }
            return true;
        }
        catch (Exception e){
            LOG.debug(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Problem> getAllProblems() {
        return problemRepo.findAll();
    }

    @Override
    public List<ProblemSubmitted> findUserProblem(int id){
        return problemSubmittedRepo.findUserQuestions(id);
    }
}
