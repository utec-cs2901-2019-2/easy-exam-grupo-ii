package com.easyexam.repository;

import com.easyexam.model.Suggest;
import com.easyexam.model.SuggestId;
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


    @Query(value = "SELECT * FROM problem p where p.",nativeQuery = true)
    int max();
}
