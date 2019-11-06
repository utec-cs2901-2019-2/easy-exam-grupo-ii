package com.easyexam.repository;

import com.easyexam.model.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ITeacherRepo extends JpaRepository<Teacher, Integer> {
}
