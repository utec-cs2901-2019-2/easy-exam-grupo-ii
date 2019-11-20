package com.easyexam.service.impl;

import com.easyexam.model.*;
import com.easyexam.model.aux.ExamCompleted;
import com.easyexam.model.aux.ProblemCompleted;
import com.easyexam.repository.ICorrelativeRepo;
import com.easyexam.repository.IExamProblemRepo;
import com.easyexam.repository.IExamRepo;
import com.easyexam.repository.IExamTeacherRepo;
import com.easyexam.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExamServiceImpl implements IExamService {

    @Autowired
    IExamRepo examRepo;

    @Autowired
    IExamProblemRepo examProblemRepo;

    @Autowired
    IExamTeacherRepo examTeacherRepo;

    @Autowired
    ICorrelativeRepo correlativeRepo;


    @Override
    public Boolean saveExam(ExamCompleted exam) {

        Exam e=new Exam();
        e.setCreationDate(new Date());
        e.setTitle(exam.getTitle());
        examRepo.save(e);
        int idExam=correlativeRepo.getIdExam();
        ExamTeacher et=new ExamTeacher(new ExamTeacherId(exam.getIdTeacher(),idExam));
        examTeacherRepo.save(et);
        for(ProblemCompleted x:exam.getListProblems()){
            ExamProblem  ep=new ExamProblem(new ExamProblemId(x.getId(),idExam),x.getScoreInteger());
            examProblemRepo.save(ep);
        }
        return true;
    }
}
