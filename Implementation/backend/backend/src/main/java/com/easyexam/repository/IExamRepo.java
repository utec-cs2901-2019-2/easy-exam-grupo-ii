package com.easyexam.repository;

import com.easyexam.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExamRepo extends JpaRepository<Exam,Integer> {

    @Override
    List<Exam> findAll();

    Exam findExamById(int id);

}
