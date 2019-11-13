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


            Problem problem=new Problem(p.getTitle(),p.getType(), p.getBody(),p.getRutaImage(),0,0);
            problemRepo.save(problem);
            LOG.info("Entro a service impl");
            LOG.info(getMaxId()+p.getDescriptionSolution());
            ProblemSubmittedId subId=new ProblemSubmittedId(p.getIdTeacher(),getMaxId());
            ProblemSubmitted ps=new ProblemSubmitted(subId,new Date());
            problemSubmittedRepo.save(ps);
            LOG.info("Entro a save problem "+p.getDescriptionSolution());

            solutionProblemRepo.save(new SolutionProblem(getMaxId(),p.getDescriptionSolution(),p.getPathImageSolution()));

            for(Topic t:p.getTopics()) {
                ProblemTopic problemTopic = new ProblemTopic(new ProblemTopicId(problem.getId(), t.getId()));
                problemTopicRepo.save(problemTopic);
            }
            return true;

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

    @Override
    public int getMaxId() {
        return problemRepo.max();
    }

}
