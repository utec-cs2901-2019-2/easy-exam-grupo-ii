package com.easyexam.repository;

import com.easyexam.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IExamRepo extends JpaRepository<Exam,Integer> {

    @Override
    List<Exam> findAll();
}
