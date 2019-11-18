package com.easyexam.controller;

import com.easyexam.model.*;
import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.CommentAndUser;
import com.easyexam.model.aux.ProblemCompleted;
import com.easyexam.model.aux.TeacherProblem;
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
        LOG.info(problemCompleted.getDescriptionSolution());
        problemService.save(problemCompleted);
        return new ApiResponse<>(200, "success", true);
    }

    @PostMapping("/updateProblemRatio")
    public ApiResponse<Problem> update(@Valid int idProblem,@Valid int rate) {
        LOG.info("Entro a update ratio problem");

        problemService.updateRateProblem(idProblem, rate);
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
    public List<ProblemCompleted> getProblemTopics(){
        return problemService.getProblemTopics();
    }

    @PostMapping("/problem/saveProblemsSelected")
    public ApiResponse<TeacherProblem> saveProblemsSelected(@Valid @RequestBody TeacherProblem prob){
        ProblemSelectedId p=new ProblemSelectedId(prob.getIdProblem(),prob.getIdTeacher());
        ProblemSelected ps=new ProblemSelected(p);
        problemService.saveProblemSelected(ps);
        return new ApiResponse<>(200, "success", true);
    }

    @GetMapping("/problem/getProblemsSelected")
    public List<ProblemCompleted> getProblemsSelected(int id){
        return problemService.getProblemSelected(id);
    }

    @GetMapping("/problem/getProblemsScore")
    public int getProblemsSelected(int idProblem, int idTeacher){
        return problemService.getTeacherScore(idProblem,idTeacher);
    }

    @PostMapping("/problem/saveTeacherScore")
    public Boolean saveProblemsSelected(@Valid @RequestBody ProblemCompleted p){
        problemService.saveTeacherScore(p.getId(),p.getIdTeacher(),p.getScoreInteger());
        return true;
    }


//    @GetMapping("/roles")
//    public List<Role> getRoles() throws AuthenticationException{
//        ArrayList<Role> rol= (ArrayList<Role>) roleService.getAllRoles();
//        LOG.info(String.valueOf(rol.indexOf(0)));
//        return roleService.getAllRoles();
//        //return (List<Role>) new ApiResponse<Object>(200, "success",roleService.getAllRoles());
//    }

}
