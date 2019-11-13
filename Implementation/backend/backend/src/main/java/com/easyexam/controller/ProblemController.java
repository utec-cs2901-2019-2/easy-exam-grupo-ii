package com.easyexam.controller;

import com.easyexam.model.*;
import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.CommentAndUser;
import com.easyexam.model.aux.ProblemCompleted;
import com.easyexam.service.ICommentService;
import com.easyexam.service.IProblemService;
import com.easyexam.service.IRoleService;
import com.easyexam.service.impl.ProblemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/problem/v1")
public class ProblemController {

    @Autowired
    IProblemService problemService;

    @Autowired
    IRoleService roleService;

    @Autowired
    ICommentService commentService;
    
    private static Logger LOG= LoggerFactory.getLogger(ProblemServiceImpl.class);


    @PostMapping("/submitProblem")
    public ApiResponse<ProblemCompleted> register(@Valid @RequestBody ProblemCompleted problemCompleted) {
        LOG.info("Entro a submit problem");
        LOG.info(problemCompleted.getBody());
        problemService.save(problemCompleted);
        return new ApiResponse<>(200, "success", true);
    }

    @GetMapping("/problem/getProblems")
    public List<Problem> getProblems() throws AuthenticationException{
        return problemService.getAllProblems();
    }

    @GetMapping("/problem/getUserProblems")
    public List<ProblemSubmitted> getUserProblems(int id){
        return problemService.findUserProblem(id);
    }

    @GetMapping("/problem/getProblemTopics")
    public List<ProblemTopic> getProblemTopics(int id){
        return problemService.getProblemTopics(id);
    }

    @GetMapping("/problem/saveProblemsSelected")
    public ApiResponse<ProblemSelected> saveProblemsSelected(@Valid @RequestBody
    ProblemSelected prob){
        LOG.info("Problema seleccionado");
        problemService.saveProblemSelected(prob);
        return new ApiResponse<>(200, "success", true);
    }

    @GetMapping("/problem/getProblemsSelected")
    public List<ProblemSelected> getProblemsSelected(int id){
        return problemService.getProblemSelected(id);
    }

//    @GetMapping("/roles")
//    public List<Role> getRoles() throws AuthenticationException{
//        ArrayList<Role> rol= (ArrayList<Role>) roleService.getAllRoles();
//        LOG.info(String.valueOf(rol.indexOf(0)));
//        return roleService.getAllRoles();
//        //return (List<Role>) new ApiResponse<Object>(200, "success",roleService.getAllRoles());
//    }

}
