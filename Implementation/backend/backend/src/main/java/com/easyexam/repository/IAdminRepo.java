package com.easyexam.repository;

import com.easyexam.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAdminRepo extends JpaRepository<Admin,Integer> {

    @Override
    List<Admin> findAll();
}
