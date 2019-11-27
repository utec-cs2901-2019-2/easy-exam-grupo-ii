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
import org.springframework.security.core.AuthenticationException;

import com.easyexam.model.aux.CommentAndUser;
import com.easyexam.service.ICommentService;
import com.easyexam.service.IProblemService;
import com.easyexam.service.IRoleService;
import com.easyexam.service.impl.ProblemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/comment/v1")
public class CommentController {
    
    @Autowired
    private ICommentService commentService;

    private static Logger LOG= LoggerFactory.getLogger(ProblemServiceImpl.class);

    @PostMapping("/submitComment")
    public ApiResponse<CommentCompleted> register(@Valid @RequestBody CommentCompleted c) {
        LOG.info("Entro a submit comment");
        CommentId cid=new CommentId(c.getIdTeacher(),c.getIdProblem());
        Comment comm=new Comment(cid,c.getDescription());
        commentService.saveComment(comm);
        return new ApiResponse<>(200, "success", true);
    }

    @GetMapping("/comment/getCommentByProblem")
    public List<CommentCompleted> getCommentByProblem(int idProb){
        return commentService.getCommentByProblem(idProb);
    }

}

