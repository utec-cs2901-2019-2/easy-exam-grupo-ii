package com.easyexam.controller;
  
import com.easyexam.model.*;
import com.easyexam.model.aux.*;
import com.easyexam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.*;


@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/suggest/v1")
public class SuggestController{
    
    @Autowired
    private ISuggestService suggestService;

    @PostMapping("/reportProblem")
    public void reportProblem(String comment, int idProblem, int idTeacher){
        suggestService.reportProblem(comment, idProblem, idTeacher);
    }

    @GetMapping("/isReported")
    public Boolean isReported(int idProblem, int idTeacher){
        Boolean existReport=suggestService.findReport(idProblem, idTeacher);
        return existReport;
    }
}
