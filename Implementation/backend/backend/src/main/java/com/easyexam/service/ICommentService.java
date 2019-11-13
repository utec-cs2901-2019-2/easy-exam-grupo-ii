package com.easyexam.service;

import com.easyexam.model.aux.CommentAndUser;

import java.util.List;

public interface ICommentService {

    List<CommentAndUser> getCommentByProblem(int idProblem);
}
