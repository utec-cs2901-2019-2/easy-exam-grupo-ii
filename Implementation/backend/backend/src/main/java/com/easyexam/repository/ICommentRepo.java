package com.easyexam.repository;

import com.easyexam.model.Comment;
import com.easyexam.model.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepo extends JpaRepository<Comment, CommentId> {

    @Override
    List<Comment> findAll();
}
