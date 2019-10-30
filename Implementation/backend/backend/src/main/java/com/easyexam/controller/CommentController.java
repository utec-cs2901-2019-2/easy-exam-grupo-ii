package com.easyexam.controller;
  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.interfaces.PBEKey;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.easyexam.model.Comment;
import com.easyexam.repository.ICommentRepo;

@RestController
@RequestMapping("/api/v1")

public class CommentController{
    @Autowired
    private ICommentRepo commentRepo;

    @GetMapping("/comment")
    public List<Comment> getAllComments(){
        return commentRepo.findAll(); 
    }

}
