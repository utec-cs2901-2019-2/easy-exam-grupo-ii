<<<<<<< HEAD
package com.easyexa.controller;

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
import java.util.ArrayList;
import java.util.List;


@RestController
//TODO change pathing
@RequestMapping("/api/probFetcher")

public class ProblemController {

    @Autowired
    private IProblemService problemService;


    @Override
    public void probFetcher() throws Exception{
        List<Problem> problems = problemService.getAllProblems();
        problems.forEach(problem->problem.toString());
    }

    @Override
    public void jsonifyProblems() throws Exception{
        problemService.jsonProblems();
    }

    jsonifyProblems();



=======
package com.easyexam.controller;

import com.easyexam.model.Problem;
import com.easyexam.model.Role;
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

    @GetMapping("/problem/getCommentByProblem")
    public List<CommentAndUser> getCommentByProblem(@RequestParam(name="idProblem",required = false)int idProblem) throws AuthenticationException{
        return commentService.getCommentByProblem(idProblem);
    }

    @GetMapping("/problem/getProblemsByUser")
    public List<Problem> getProblemsByUser(@RequestParam(name="idUser",required = false)int idUser) throws AuthenticationException{
        return problemService.getAllProblemsByUsers(idUser);
    }


//    @GetMapping("/roles")
//    public List<Role> getRoles() throws AuthenticationException{
//        ArrayList<Role> rol= (ArrayList<Role>) roleService.getAllRoles();
//        LOG.info(String.valueOf(rol.indexOf(0)));
//        return roleService.getAllRoles();
//        //return (List<Role>) new ApiResponse<Object>(200, "success",roleService.getAllRoles());
//    }
>>>>>>> origin/develop

}
