package com.easyexam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.interfaces.PBEKey;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.easyexam.model.User;
import com.easyexam.repository.IUserRepo;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private IUserRepo userRepo;

    @GetMapping("/user")
    public List<User> getAllEmployees() {
        return userRepo.findAll();
    }

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User user) {
        return userRepo.save(user);
    }
    
    @PostMapping("/user")
    public boolean user validateUser(@Valid @RequestBody User user){
        User temp = userRepo.find;
        returnRepo.save(user);
    }

}
