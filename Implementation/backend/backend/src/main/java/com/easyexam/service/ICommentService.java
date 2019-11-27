package com.easyexam.service;

import com.easyexam.model.Comment;
import com.easyexam.model.aux.CommentCompleted;
import com.easyexam.repository.ICommentRepo;
import com.easyexam.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.easyexam.model.aux.CommentAndUser;

import java.util.List;

public interface ICommentService {

    List<CommentCompleted> getCommentByProblem(int idProblem);

    Boolean saveComment(Comment c);
}
