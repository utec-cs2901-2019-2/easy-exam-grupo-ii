package com.easyexam.service.impl;

import com.easyexam.model.*;
import com.easyexam.model.aux.ProblemCompleted;
import com.easyexam.repository.*;
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

    @Autowired
    IProblemSelectedRepo problemSelectedRepo;

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

    @Override
    public List<ProblemTopic> getProblemTopics(int idProb){
        return problemTopicRepo.getProblemTopics(idProb);
    }

    @Override
    public Boolean saveProblemSelected(ProblemSelected promSecl) {
        try{
            problemSelectedRepo.save(promSecl);
            return true;
        }
        catch (Exception e){
            LOG.warn(e.getMessage());
            return false;
        }
    }

    @Override
    public List<ProblemSelected> getProblemSelected(int idUser){
        return problemSelectedRepo.getSelectedProblems(idUser);
    }
}
