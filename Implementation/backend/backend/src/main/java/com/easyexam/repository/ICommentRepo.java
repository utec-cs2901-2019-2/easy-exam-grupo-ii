package com.easyexam.repository;

import com.easyexam.model.Comment;
import com.easyexam.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ICommentRepo extends JpaRepository<Comment, Integer> {

    @Override
    List<Comment> findAll();

    @Query (value = "SELECT * FROM comments", nativeQuery = true)
    List<Comment> findCustom();

}
