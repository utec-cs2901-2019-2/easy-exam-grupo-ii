package com.easyexam.service.impl;

import com.easyexam.model.Comment;
import com.easyexam.model.aux.CommentAndUser;
import com.easyexam.repository.ICommentRepo;
import com.easyexam.repository.ITeacherRepo;
import com.easyexam.service.ICommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {


    private static Logger LOG= LoggerFactory.getLogger(ProblemServiceImpl.class);

    @Autowired
    ICommentRepo commentRepo;

    @Autowired
    ITeacherRepo teacherRepo;

    @Override
    public List<CommentAndUser> getCommentByProblem(int idProblem) {
        LOG.info("ENtro a CommenByProblem para Service");
        List<Comment> listComment=commentRepo.findAllByCommentId_IdProblem(idProblem);
        List<CommentAndUser> commentAndUsers=new ArrayList<CommentAndUser>();
        for(Comment c:listComment){
            CommentAndUser commentByUser=new CommentAndUser();
            commentByUser.setIdTeacher(c.getCommentId().getIdTeacher());
            commentByUser.setIdProblem(c.getCommentId().getIdProblem());
            commentByUser.setNameTeacher(teacherRepo.findTeacherById(c.getCommentId().getIdTeacher()).getFirstname());
            commentByUser.setDescriptionComment(c.getDescription());
            commentAndUsers.add(commentByUser);
        }
        LOG.info("Salio a CommenByProblem para Service");
        return commentAndUsers;
    }
}
