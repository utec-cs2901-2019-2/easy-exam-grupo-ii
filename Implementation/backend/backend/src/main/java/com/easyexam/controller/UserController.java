package com.easyexam.controller;

import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.UserCompleted;
import com.easyexam.service.IProblemService;
import com.easyexam.service.ITeacherService;
import com.easyexam.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @Transactional
    @PostMapping("/teacher/updateBonus")
    public ApiResponse<UserCompleted> updateBonus(@Valid @RequestBody UserCompleted user){
        teacherService.updateBonus(user.getBonus(),user.getId());
        return new ApiResponse<>(200, "success", true);
    }


}
