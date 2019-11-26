package com.easyexam.controller;

import com.easyexam.model.Admin;
import com.easyexam.model.User;
import com.easyexam.model.aux.*;
import com.easyexam.service.IAdminService;
import com.easyexam.service.IRoleService;
import com.easyexam.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin/v1")
public class AdminController {
    
    @Autowired
    private IAdminService service;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping("/")
    public List<Admin> getAllAdmins(){
        return service.getAllAdmins();
    }

    @PostMapping("register-admin")
    public ApiResponse<AuthToken> createAdmin(@Valid @RequestBody Admin admin) {
        User userDetail = admin.getUser();
        
        if (userService.findByEmail(userDetail.getEmail()) != null) {
            return new ApiResponse<>(200, "fail", null);
        }

        userDetail.setActive(true);
        userDetail.setRole(roleService.findById(0)); // Teacher 1 - Admin 0

        User user = userService.save(userDetail);
        admin.setUser(user);

        Admin adminFinal = service.save(admin);
        
        userService.updatePassword(user.getId());
        user.setAdmin(adminFinal);
        userService.update(user);
        return new ApiResponse<>(200, "success",null);
    }
}

