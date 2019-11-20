package com.easyexam.controller;

import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.UserCompleted;
import com.easyexam.repository.IUserRepo;
import com.easyexam.service.IProblemService;
import com.easyexam.service.ITeacherService;
import com.easyexam.service.IUserService;
import com.easyexam.service.impl.ProblemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user/v1")
public class UserController {

    @Autowired
    ITeacherService teacherService;

    @Autowired
    IUserService userService;

    private static Logger LOG= LoggerFactory.getLogger(ProblemServiceImpl.class);


    @GetMapping("/user/getDates")
    public UserCompleted getUserDates(String email){
        LOG.info("Imprime correo  "+ email);
        return userService.getDates(email);
    }

    @GetMapping("/user/getReportedUsers")
    public List<UserCompleted> getReportedUsers(){
        return userService.getReportedUsers();
    }



}
