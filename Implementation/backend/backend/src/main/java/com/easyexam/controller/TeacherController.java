package com.easyexam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.easyexam.service.UserService;
import com.easyexam.service.TeacherService;
import com.easyexam.model.Teacher;
import com.easyexam.model.User;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class TeacherController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/signup")
    public User createUser(@Valid @RequestBody Teacher teacher) {
        User user = teacher.getUser();
        if (userService.findByEmail(user.getEmail()) == null) {
            return null;
        }
        
        User userDetail = userService.save(user);
        Teacher teacherDetail = teacherService.save(teacher);

        userService.updatePassword(userDetail.getId());
        
        userDetail.setTeacher(teacherDetail);
        teacherDetail.setUser(userDetail);

        userService.update(userDetail);
        teacherService.update(teacherDetail);
        return userDetail;
    }
}