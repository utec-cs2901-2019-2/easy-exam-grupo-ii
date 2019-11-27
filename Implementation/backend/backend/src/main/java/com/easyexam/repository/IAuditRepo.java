package com.easyexam.repository;

import com.easyexam.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuditRepo extends JpaRepository<Audit,Integer> {

    @Override
    List<Audit> findAll();

}
