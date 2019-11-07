package com.easyexam.repository;

import com.easyexam.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITeacherRepo extends JpaRepository<Teacher,Integer> {
    @Override
    List<Teacher> findAll();
}
