package com.easyexam.service.impl;

import com.easyexam.model.*;
import com.easyexam.model.aux.ProblemCompleted;
import com.easyexam.repository.*;
import com.easyexam.service.IProblemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    @Autowired
    ITopicRepo topicRepo;

    @Autowired
    ITeacherScoreRepo teacherScoreRepo;

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
    public List<ProblemCompleted> getProblemTopics(){
        List<Problem> pt = problemRepo.findAll();
        List<ProblemCompleted> pt2 = new ArrayList<ProblemCompleted>();

        for (Problem p : pt) {
            ProblemCompleted tmp=new ProblemCompleted();
            tmp.setBody(p.getBody());
            tmp.setTitle(p.getTitle());
            tmp.setType(p.getType());
            tmp.setScore(p.getScore());
            tmp.setQualifiers(p.getQualifiers());
            tmp.setId(p.getId());
            List<ProblemTopic> ptopics=problemTopicRepo.findAllByProblemTopicId_IdProblem(p.getId());
            List<String> topics=new ArrayList<>();
            for (ProblemTopic ptopic:ptopics) {
                Topic t=topicRepo.findTopicById(ptopic.getProblemTopicId().getIdTopic());
                topics.add(t.getName());
            }
            tmp.setTopicsString(topics);
            pt2.add(tmp);
        }
        return pt2;
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
    public List<ProblemCompleted> getProblemSelected(int idUser){
        List<ProblemSelected> tmp=problemSelectedRepo.findAllByProblemSelectedId_IdTeacher(idUser);
        List<ProblemCompleted> list=new ArrayList<>();
        for(ProblemSelected ps:tmp){
            Problem p=problemRepo.findProblemById(ps.getProblemSelectedId().getIdProblem());
            ProblemCompleted tmp2=new ProblemCompleted();
            tmp2.setBody(p.getBody());
            tmp2.setTitle(p.getTitle());
            tmp2.setType(p.getType());
            tmp2.setScore(p.getScore());
            tmp2.setQualifiers(p.getQualifiers());
            tmp2.setId(p.getId());
            List<ProblemTopic> ptopics=problemTopicRepo.findAllByProblemTopicId_IdProblem(p.getId());
            List<String> topics=new ArrayList<>();
            for (ProblemTopic ptopic:ptopics) {
                Topic t=topicRepo.findTopicById(ptopic.getProblemTopicId().getIdTopic());
                topics.add(t.getName());
            }
            tmp2.setTopicsString(topics);
            list.add(tmp2);
        }

        return list;
    }

    @Override
    public int getMaxId() {
        return problemRepo.max();
    }

    @Override
    public void updateRateProblem(int idProblem, int rate) {
        Problem p=problemRepo.findProblemById(idProblem);
        float rateCurrent=p.getScore()*p.getQualifiers();
        rateCurrent+=rate;
        rateCurrent/=(p.getQualifiers()+1);
        p.setScore(rateCurrent);
        p.setQualifiers(p.getQualifiers()+1);
        problemRepo.save(p);
    }

    @Override
    public int getTeacherScore(int idProblem, int idTeacher) {
        TeacherScore ts=teacherScoreRepo.findTeacherScoreByTeacherScoreIdIdProblemAndTeacherScoreId_IdTeacher(idProblem,idTeacher);
        return (ts==null)?0:ts.getScore();
    }

    @Override
    public Boolean saveTeacherScore(int idProblem, int idTeacher, int score) {
        teacherScoreRepo.save(new TeacherScore(new TeacherScoreId(idProblem,idTeacher),score));
        return true;
    }

}
