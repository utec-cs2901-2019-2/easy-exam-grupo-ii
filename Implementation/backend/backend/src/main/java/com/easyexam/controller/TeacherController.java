package com.easyexam.controller;

import com.easyexam.model.Teacher;
import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.UserCompleted;
import com.easyexam.service.ITeacherService;
import com.easyexam.service.impl.ProblemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/teacher/v1")
public class TeacherController {

    @Autowired
    ITeacherService teacherService;

    private static Logger LOG= LoggerFactory.getLogger(ProblemServiceImpl.class);


    @Transactional
    @PostMapping("/teacher/updateBonus")
    public ApiResponse<UserCompleted> updateBonus(@Valid @RequestBody UserCompleted user){
        teacherService.updateBonus(user.getBonus(),user.getId());
        return new ApiResponse<>(200, "success", true);
    }

    @Transactional
    @PutMapping("/teacher/update")
    public ApiResponse<Teacher> updateTeacher(@Valid @RequestBody Teacher teacher){
        teacherService.update(teacher);
        return new ApiResponse<>(200, "success", true);
    }

    @GetMapping("/teacher/getDatos")
    public Teacher getDatosTeacher(@Valid int idTeacher){
        return teacherService.findOne(idTeacher);
    }



}
