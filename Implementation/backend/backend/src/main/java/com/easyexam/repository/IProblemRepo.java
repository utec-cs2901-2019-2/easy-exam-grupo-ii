package com.easyexam.repository;

import com.easyexam.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProblemRepo extends JpaRepository<Problem,Integer> {
    @Override
    List<Problem> findAll();

    Problem findProblemById(int id);


    Problem findProblemByTitle(String title);

    @Query(value = "SELECT max(p.id) FROM problem p",nativeQuery = true)
    int max();


}
