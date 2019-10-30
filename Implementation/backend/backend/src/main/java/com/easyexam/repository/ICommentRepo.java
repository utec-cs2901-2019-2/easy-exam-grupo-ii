package com.easyexam.repository;

import com.easyexam.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICommentRepo extends JpaRepository<Comment, int>{
}
