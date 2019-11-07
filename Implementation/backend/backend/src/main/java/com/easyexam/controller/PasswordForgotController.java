package com.easyexam.controller;

import com.easyexam.model.*;
import com.easyexam.model.aux.*;
import com.easyexam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class PasswordForgotController {

    @Autowired private UserService userService;
    @Autowired private PasswordTokenService tokenService;
    @Autowired private EmailService emailService;

    @PostMapping("forgot-password")
    public String processForgotPasswordForm(@Valid @RequestBody User form, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()){
            return "forgot-password";
        }

        User user = userService.findByEmail(form.getEmail());
        if (user == null){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgot-password";
        }
        
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenService.save(token);

        Mail mail = new Mail();
        mail.setFrom("iot3carrito@gmail.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");
        
        Map<String, Object> model = new HashMap<>();
        
        model.put("token", token);
        model.put("signature", "https://memorynotfound.com");

        if (user.getTeacher() != null) {
            model.put("fullName", user.getTeacher().getFirstName() + ' ' +user.getTeacher().getLastName());
        } else {
            model.put("fullName", user.getAdmin().getFullName());
        }

        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());

        mail.setModel(model);
        emailService.sendEmail(mail);
        return "OK";
        /*
        return "redirect:/forgot-password?success";*/
    }
}