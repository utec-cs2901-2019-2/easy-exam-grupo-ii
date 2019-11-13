package com.easyexam.service.impl;

import com.easyexam.model.SolutionProblem;
import com.easyexam.repository.ISolutionProblemRepo;
import com.easyexam.service.ISolutionProblemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionProblemServiceImpl implements ISolutionProblemService {

    @Autowired
    ISolutionProblemRepo solutionProblemRepo;

    private static Logger LOG= LoggerFactory.getLogger(ProblemServiceImpl.class);

    @Override
    public Boolean saveSolutionProblem(SolutionProblem solProbl) {
        try{
            solutionProblemRepo.save(solProbl);
            return true;
        }
        catch (Exception e){
            LOG.warn(e.getMessage());
            return false;
        }

    }
}
