package com.easyexam.repository;

import com.easyexam.model.Correlativo;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICorrelativeRepo extends JpaRepository<Correlativo,Integer> {

    @Query(value = "select id_user from correlative",nativeQuery = true)
    int getIdUser();

    @Query(value = "update correlative set id_user=?1",nativeQuery = true)
    void updateIdUser(int idUser);

    @Query(value = "select id_problem from correlative",nativeQuery = true)
    int getIdProblem();

    @Query(value = "update correlative set id_problem=?1",nativeQuery = true)
    void updateIdProblem(int idProblem);

    @Query(value = "select id_exam from correlative",nativeQuery = true)
    int getIdExam();

    @Query(value = "update correlative set id_exam=?1",nativeQuery = true)
    void updateIdExam(int idExam);

}
