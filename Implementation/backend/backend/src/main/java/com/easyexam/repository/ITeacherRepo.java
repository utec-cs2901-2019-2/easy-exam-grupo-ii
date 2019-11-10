package com.easyexam.repository;

import com.easyexam.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITeacherRepo extends JpaRepository<Teacher,Integer> {
    @Override
    List<Teacher> findAll();
    Teacher findTeacherById(int id);
}
