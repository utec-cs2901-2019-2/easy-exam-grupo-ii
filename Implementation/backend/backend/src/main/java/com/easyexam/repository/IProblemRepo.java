package com.easyexam.repository;

import com.easyexam.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProblemRepo extends JpaRepository<Problem,Integer> {
    @Override
    List<Problem> findAll();

    @Query("Select * From problem")
    List<Problem> findCustom();
}
