package com.easyexam.service.impl;

import com.easyexam.model.Comment;
import com.easyexam.repository.ICommentRepo;
import com.easyexam.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepo commentRepo;

    @Override
    public List<Comment> customQuery(){
        return commentRepo.findCustom();
    }

}
