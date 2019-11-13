package com.easyexam.repository;

import com.easyexam.model.ProblemSelected;
import com.easyexam.model.ProblemSelectedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProblemSelectedRepo extends JpaRepository<ProblemSelected, ProblemSelectedId> {
}
