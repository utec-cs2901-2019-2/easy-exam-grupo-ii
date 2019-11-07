package com.easyexam.service.impl;

import com.easyexam.repository.IExamProblemRepo;
import com.easyexam.repository.IExamRepo;
import com.easyexam.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements IExamService {

    @Autowired
    IExamRepo examRepo;

    @Autowired
    IExamProblemRepo examProblemRepo;


}
