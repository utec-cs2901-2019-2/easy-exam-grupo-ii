package com.easyexam.repository;

import com.easyexam.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAuditRepo extends JpaRepository<Audit,Integer> {

    @Override
    List<Audit> findAll();

}
