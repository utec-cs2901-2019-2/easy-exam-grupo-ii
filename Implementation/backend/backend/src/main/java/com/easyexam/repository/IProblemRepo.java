package com.easyexam.repository;

import com.easyexam.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProblemRepo extends JpaRepository<Problem,Integer> {
}
