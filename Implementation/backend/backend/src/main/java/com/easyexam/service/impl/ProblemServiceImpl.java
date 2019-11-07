package com.easyexam.service.impl;

import com.easyexam.repository.IProblemRepo;
import com.easyexam.repository.IProblemSubmittedRepo;
import com.easyexam.service.IProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemServiceImpl implements IProblemService {

    @Autowired
    IProblemRepo problemRepo;

    @Autowired
    IProblemSubmittedRepo problemSubmittedRepo;

}
