package com.easyexam.repository;

import com.easyexam.model.ProblemSubmitted;
import com.easyexam.model.ProblemSubmittedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

@Repository
public interface IProblemSubmittedRepo extends JpaRepository<ProblemSubmitted, ProblemSubmittedId> {

    @Override
    List<ProblemSubmitted> findAll();

    List<ProblemSubmitted> findAllByProblemSubmittedId_IdUser(int idUser);

}
