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
import com.easyexam.service.IRoleService;
import com.easyexam.config.JwtTokenUtil;
import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.AuthToken;
import com.easyexam.model.aux.Mail;
import com.easyexam.model.aux.PasswordResetDto;
import com.easyexam.model.User;
import com.easyexam.service.implOthers.EmailService;
import com.easyexam.service.implOthers.PasswordTokenService;
import com.easyexam.service.ITeacherService;
import com.easyexam.model.aux.PasswordResetToken;
import com.easyexam.model.Role;
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

    @Autowired
    private IRoleService roleService;

    @PostMapping("/login")
    public ApiResponse<AuthToken> login(@RequestBody User loginUser) throws AuthenticationException {
        final User user = userService.findByEmail(loginUser.getEmail());
        
        if (user == null || user.getActive() == false) {
            return new ApiResponse<>(200, "fail", null);
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
        final String token = jwtTokenUtil.generateToken(user);
        final Teacher teacher = teacherService.findOneByUser(user);
        return new ApiResponse<>(200, "success",new AuthToken(token, user.getId(), user.getEmail(), teacher.getBonus(), user.getRole()));
    }

    @PostMapping("/register")
    public ApiResponse<AuthToken> register(@Valid @RequestBody Teacher teacherDetail) {
        User userDetail = teacherDetail.getUser();
        
        if (userService.findByEmail(userDetail.getEmail()) != null) {
            return new ApiResponse<>(200, "fail", null);
        }

        userDetail.setActive(true);
        if (roleService.getAllRoles().size() == 0) {
            Role teacher = new Role(1, "Teacher");
            Role admin = new Role(0, "Admin");

            roleService.save(teacher);
            roleService.save(admin);
        }
        userDetail.setRole(roleService.findById(1)); // Teacher 1 - Admin 0

        User user = userService.save(userDetail);
        teacherDetail.setUser(user);
        teacherDetail.setBonus(3);

        Teacher teacher = teacherService.save(teacherDetail);
        
        userService.updatePassword(user.getId());
        user.setTeacher(teacher);
        userService.update(user);

        return new ApiResponse<>(200, "success",null);
    }

    @PostMapping("forgot-password")
    public ApiResponse<?> processForgotPasswordForm(@Valid @RequestBody User form, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()){
            return new ApiResponse<>(200, "error",null);
        }

        User user = userService.findByEmail(form.getEmail());
        if (user == null){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return new ApiResponse<>(200, "fail",null);
        }
        
        PasswordResetToken token = tokenService.findOneByUser(user);

        if (token == null) {
            token = new PasswordResetToken();
            token.setToken(UUID.randomUUID().toString());
            token.setUser(user);
            token.setExpiryDate(30);
            tokenService.save(token);
        }

        Mail mail = new Mail();
        mail.setFrom("easyexam.web@gmail.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");
        
        Map<String, Object> model = new HashMap<>();
        
        model.put("token", token);
        model.put("signature", "EasyExam");

        if (user.getTeacher() != null) {
            model.put("fullName", user.getTeacher().getFirstname() + ' ' +user.getTeacher().getLastname());
        } else {
            model.put("fullName", user.getAdmin().getFullname());
        }

        String url = request.getScheme() + "://" + request.getServerName() + ":8080";// + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());

        mail.setModel(model);
        emailService.sendEmail(mail);
        
        return new ApiResponse<>(200, "success", null);
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