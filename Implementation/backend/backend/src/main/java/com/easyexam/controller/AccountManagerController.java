package com.easyexam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import com.easyexam.service.UserService;
import com.easyexam.config.JwtTokenUtil;
import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.AuthToken;
import com.easyexam.model.User;
import com.easyexam.service.TeacherService;
import com.easyexam.model.Teacher;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class AccountManagerController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ApiResponse<AuthToken> login(@RequestBody User loginUser) throws AuthenticationException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));

        final User user = userService.findByEmail(loginUser.getEmail());
        final String token = jwtTokenUtil.generateToken(user);
        return new ApiResponse<>(200, "success",new AuthToken(token, user.getEmail()));
    }

    @PostMapping("/register")
    public ApiResponse<User> register(@Valid @RequestBody Teacher teacherDetail) {
        User userDetail = teacherDetail.getUser();
        
        if (userService.findByEmail(userDetail.getEmail()) != null) {
            return null;
        }

        userDetail.setActivate(true);

        User user = userService.save(userDetail);
        teacherDetail.setUser(user);

        Teacher teacher = teacherService.save(teacherDetail);
        
        userService.updatePassword(user.getId());
        user.setTeacher(teacher);
        userService.update(user);

        return new ApiResponse<>(200, "success", userDetail);
    }
}