package com.easyexam.controller;

import java.util.List;

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
import com.easyexam.model.User;
import com.easyexam.repository.IUserRepo;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getAllEmployees() {
        return userRepo.findAll();
    }

    @PostMapping("/signup")
    public User createUser(@Valid @RequestBody User user) {
        User userDetail = userRepo.save(user);
        userService.updatePassword(userDetail.getId());
        return userDetail;
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?>  updateUser(@PathVariable Integer id) {
        return userRepo.findById(id).map(user -> {
                    /* Falta implementar */
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?>  deleteUser(@PathVariable Integer id) {
        return userRepo.findById(id).map(user -> {
                    userRepo.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }
}