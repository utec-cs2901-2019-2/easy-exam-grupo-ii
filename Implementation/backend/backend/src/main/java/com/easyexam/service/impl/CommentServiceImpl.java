package com.easyexam.service.impl;

import com.easyexam.repository.ICommentRepo;
import com.easyexam.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepo commentRepo;
}
