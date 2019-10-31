package com.easyexam.controller;

import com.easyexam.service.UsuarioService;
import com.easyexam.config.JwtTokenUtil;
import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.AuthToken;
import com.easyexam.model.aux.Login;
import com.easyexam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/generate-token")
    public ApiResponse<AuthToken> register(@RequestBody Login loginUser) throws AuthenticationException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));

        final User user = usuarioService.findOneByEmail(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return new ApiResponse<>(200, "success",new AuthToken(token, user.getEmail()));
    }
}