package com.easyexam.repository;

import com.easyexam.model.Suggest;
import com.easyexam.model.SuggestId;
import com.easyexam.model.aux.UserCompleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ISuggestRepo extends JpaRepository<Suggest,SuggestId> {

    @Override
    List<Suggest> findAll();

    @Modifying
    @Query(value="insert into suggest values (:idProblem, :idTeacher, :comment, true)", nativeQuery=true)
    int reportProblem(@Param("comment") String comment, @Param("idProblem")int idProblem, @Param("idTeacher")int idTeacher);


    @Query(value = "select s.id_teacher as id, count(*) as cantidad from suggest s where  s.is_reported=true group by(s.id_teacher) having count(*)>=1 ",nativeQuery = true)
    List<UserCompleted> getSuggest();

}
