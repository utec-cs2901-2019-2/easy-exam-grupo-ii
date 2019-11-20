package com.easyexam.controller;

import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.ExamCompleted;
import com.easyexam.model.aux.ProblemCompleted;
import com.easyexam.service.IExamService;
import com.easyexam.service.impl.ProblemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/exam/v1")
public class ExamController {


    private static Logger LOG= LoggerFactory.getLogger(ProblemServiceImpl.class);

    @Autowired
    IExamService examService;

    @PostMapping("/submitExam")
    public ApiResponse<ExamCompleted> register(@Valid @RequestBody ExamCompleted examCompleted) {
        LOG.info("Entro a submit problem");
        LOG.info(examCompleted.getTitle());
        examService.saveExam(examCompleted);
        return new ApiResponse<>(200, "success", true);
    }

}
