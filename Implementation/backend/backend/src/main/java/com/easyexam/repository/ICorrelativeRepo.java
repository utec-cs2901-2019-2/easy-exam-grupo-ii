package com.easyexam.repository;

import com.easyexam.model.Correlativo;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICorrelativeRepo extends JpaRepository<Correlativo,Integer> {

    @Query(value = "select id_user from correlative",nativeQuery = true)
    int getIdUser();

    @Modifying
    @Transactional
    @Query(value = "update correlative set id_user=id_user+1",nativeQuery = true)
    void updateIdUser();

    @Query(value = "select id_problem from correlative",nativeQuery = true)
    int getIdProblem();

    @Modifying
    @Transactional
    @Query(value = "update correlative set id_problem=id_problem+1",nativeQuery = true)
    void updateIdProblem();

    @Query(value = "select id_exam from correlative",nativeQuery = true)
    int getIdExam();

    @Modifying
    @Transactional
    @Query(value = "update correlative set id_exam=id_exam+1",nativeQuery = true)
    void updateIdExam();

    @Query(value = "select id_topic from correlative",nativeQuery = true)
    int getIdTopic();

    @Modifying
    @Transactional
    @Query(value = "update correlative set id_topic=id_topic+1",nativeQuery = true)
    void updateIdTopic();
}
