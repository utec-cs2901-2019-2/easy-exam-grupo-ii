package com.easyexam.controller;

import java.util.List;

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

    @PostMapping("/users")
    public Boolean validateUser(@Valid @RequestBody User user) {
        User temp = userRepo.findAllByEmail(user.getEmail());
        return temp.getPassword() == user.getPassword();
    }
}