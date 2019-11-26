package com.easyexam.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.easyexam.model.*;
import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.ProblemCompleted;
import com.easyexam.model.aux.TeacherProblem;
import com.easyexam.service.ICommentService;
import com.easyexam.service.IProblemService;
import com.easyexam.service.IRoleService;
import com.easyexam.service.impl.ProblemServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.caliope.udc.DocumentConverter;
import club.caliope.udc.InputFormat;
import club.caliope.udc.OutputFormat;

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

    private static Logger LOG = LoggerFactory.getLogger(ProblemController.class);

    @PostMapping("/submitProblem")
    public ApiResponse<ProblemCompleted> register(@Valid @RequestBody ProblemCompleted problemCompleted) {
        LOG.info("Entro a submit problem");
        LOG.info(problemCompleted.getBody());
        LOG.info(problemCompleted.getDescriptionSolution());
        problemService.save(problemCompleted);
        return new ApiResponse<>(200, "success", true);
    }

    @PostMapping("/updateProblemRatio")
    public ApiResponse<Problem> update(@Valid int idProblem, @Valid int rate) {
        LOG.info("Entro a update ratio problem");

        problemService.updateRateProblem(idProblem, rate);
        return new ApiResponse<>(200, "success", true);
    }

    @GetMapping("/problem/getProblems")
    public List<Problem> getProblems() throws AuthenticationException {
        return problemService.getAllProblems();
    }

    @GetMapping("/problem/getUserProblems")
    public List<ProblemSubmitted> getUserProblems(int id) {
        return problemService.findUserProblem(id);
    }

    @GetMapping("/problem/getProblemTopics")
    public List<ProblemCompleted> getProblemTopics() {
        return problemService.getProblemTopics();
    }

    @PostMapping("/problem/saveProblemsSelected")
    public ApiResponse<TeacherProblem> saveProblemsSelected(@Valid @RequestBody TeacherProblem prob) {
        ProblemSelectedId p = new ProblemSelectedId(prob.getIdProblem(), prob.getIdTeacher());
        ProblemSelected ps = new ProblemSelected(p);
        problemService.saveProblemSelected(ps);
        return new ApiResponse<>(200, "success", true);
    }

    @GetMapping("/problem/getProblemsSelected")
    public List<ProblemCompleted> getProblemsSelected(int id) {
        return problemService.getProblemSelected(id);
    }

    @GetMapping("/problem/getProblemsScore")
    public int getProblemsSelected(int idProblem, int idTeacher) {
        return problemService.getTeacherScore(idProblem, idTeacher);
    }

    @PostMapping("/problem/saveTeacherScore")
    public Boolean saveProblemsSelected(@Valid @RequestBody ProblemCompleted p) {
        problemService.saveTeacherScore(p.getId(), p.getIdTeacher(), p.getScoreInteger());
        return true;
    }

    @GetMapping("/problem/latexToHtml")
    public String latexToHtml(int idProblem) throws IOException {
        Problem problem = problemService.findProblemById(idProblem);
        
        File input = new File("sample.tex");
        File output = new File("sample.html");
        
        FileWriter writer = new FileWriter(input);
        writer.write(problem.getBody());
        writer.close();
        
        Runtime.getRuntime().exec("pandoc " + input.getPath() + " --from=latex --to=html5 -s --output=" + output.getPath());

        BufferedReader reader = new BufferedReader(new FileReader(output));
        String line = reader.readLine();
        String preview = "";
        
        while ((line = reader.readLine()) != null) {
            preview = preview + '\n' + line;
        }
        
        reader.close();
        
        return preview;
    }

    @PostMapping("/problem/latexToHtmlbyBody")
    public String latexToHtmlbyBody(@RequestBody Map<String, String> data) throws IOException {

        String body = data.get("body");

        if (body == null) {
            return "Error";
        }
        
        File input = new File("sample.tex");
        File output = new File("sample.html");
        
        FileWriter writer = new FileWriter(input);
        writer.write(body);
        writer.close();
        
        Runtime.getRuntime().exec("pandoc " + input.getPath() + " --from=latex --to=html5 -s --output=" + output.getPath());

        BufferedReader reader = new BufferedReader(new FileReader(output));
        String line = reader.readLine();
        String preview = "";
        
        while ((line = reader.readLine()) != null) {
            preview = preview + '\n' + line;
        }
        
        reader.close();
        
        return preview;
    }

    @GetMapping("/problem/getSolutionProblem")
    public SolutionProblem getSolutionProblem(int idProblem) {
        return problemService.getSolutionOfProblem(idProblem);
    }

    @GetMapping("/problem/getProblemsSubmitedByUser")
    public List<ProblemCompleted> getProblemsSubmited(int idUser) {
        return problemService.getListProblemCompletedSubmitedByUser(idUser);
    }

    @GetMapping("/reportedProblems")
    public List<Problem> getReportedProblems() {
        return problemService.getReportedProblems();
    }

    @DeleteMapping("/deleteProblem")
    public String deleteProblems(int idProblem){
        problemService.delete(idProblem);
        return "deleted";
    }

//    @GetMapping("/roles")
//    public List<Role> getRoles() throws AuthenticationException{
//        ArrayList<Role> rol= (ArrayList<Role>) roleService.getAllRoles();
//        LOG.info(String.valueOf(rol.indexOf(0)));
//        return roleService.getAllRoles();
//        //return (List<Role>) new ApiResponse<Object>(200, "success",roleService.getAllRoles());
//    }

}
