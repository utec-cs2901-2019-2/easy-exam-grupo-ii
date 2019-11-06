package com.easyexam.controller;

import com.easyexam.model.*;
import com.easyexam.model.aux.*;
import com.easyexam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @Autowired private UserService userService;
    @Autowired private PasswordResetService passwordResetService;

    @PostMapping
    public String processForgotPasswordForm(@Valid @RequestBody Login loginUser) {

        User user = userService.findByEmail(loginUser.getUsername());
        if (user == null){
            return "forgot-password";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        passwordResetService.save(token);

        return "redirect:/forgot-password?success";

    }
}