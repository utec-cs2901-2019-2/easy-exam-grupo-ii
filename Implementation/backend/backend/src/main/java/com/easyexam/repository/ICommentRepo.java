package com.easyexam.repository;

import com.easyexam.model.Comment;
import com.easyexam.model.CommentId;
import com.easyexam.model.aux.CommentAndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepo extends JpaRepository<Comment, CommentId> {

    @Override
    List<Comment> findAll();

//    @Query("select c.id_poblem as idProblem,t.user_id as idTeacher,t.first_name as nameTeacher,c.description as descriptionComment from comment c, teacher t where c.id_teacher=t.user_id and c.id_problem=?1")
//    List<CommentAndUser> getCommentByProblem(int idProblem);

    List<Comment> findAllByCommentId_IdProblem(int idProblem);
}
