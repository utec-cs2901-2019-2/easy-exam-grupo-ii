package com.easyexam.controller;

import com.easyexam.model.aux.UserCompleted;
import com.easyexam.service.IProblemService;
import com.easyexam.service.ITeacherService;
import com.easyexam.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user/v1")
public class UserController {

    @Autowired
    ITeacherService teacherService;

    @GetMapping("/teacher/getDates")
    public UserCompleted getUserDates(String email){
        return teacherService.getDates(email);
    }
}
