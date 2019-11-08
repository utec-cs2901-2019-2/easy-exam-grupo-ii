package com.easyexam.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;

import com.easyexam.service.IUserService;
import com.easyexam.config.JwtTokenUtil;
import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.AuthToken;
import com.easyexam.model.aux.Mail;
import com.easyexam.model.aux.PasswordResetDto;
import com.easyexam.model.User;
import com.easyexam.service.EmailService;
import com.easyexam.service.PasswordTokenService;
import com.easyexam.service.ITeacherService;
import com.easyexam.model.PasswordResetToken;
import com.easyexam.model.Teacher;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class AccountManagerController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private PasswordTokenService tokenService;

    @Autowired
    private EmailService emailService;

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

        userDetail.setActive(true);

        User user = userService.save(userDetail);
        teacherDetail.setUser(user);

        Teacher teacher = teacherService.save(teacherDetail);
        
        userService.updatePassword(user.getId());
        user.setTeacher(teacher);
        userService.update(user);

        return new ApiResponse<>(200, "success", userDetail);
    }

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
            model.put("fullName", user.getTeacher().getFirstname() + ' ' +user.getTeacher().getLastname());
        } else {
            model.put("fullName", user.getAdmin().getFullname());
        }

        String url = request.getScheme() + "://" + request.getServerName() + ":8080";// + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());

        mail.setModel(model);
        emailService.sendEmail(mail);
        
        return "redirect:/forgot-password?success";
    }


    @Transactional
    @PostMapping ("/reset-password")
    public String handlePasswordReset(@RequestBody @Valid PasswordResetDto form,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            return "redirect:/reset-password?token=" + form.getToken();
        }

        PasswordResetToken token = tokenService.findOne(form.getToken());
        User user = token.getUser();
        
        user.setPassword(form.getPassword());
        userService.update(user);
        userService.updatePassword(user.getId());
        
        tokenService.delete(token);

        return "redirect:/login?resetSuccess";
    }
}