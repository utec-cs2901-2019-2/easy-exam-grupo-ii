package com.easyexam.service;

import com.easyexam.model.aux.CommentAndUser;

import javax.validation.constraints.Min;
import java.util.List;

public interface ICommentService {

    List<CommentAndUser> getCommentByProblem(int idProblem);
}
