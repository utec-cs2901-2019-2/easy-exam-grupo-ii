package com.easyexam.repository;

import com.easyexam.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoleRepo extends JpaRepository<Role,Integer> {
    @Override
    List<Role> findAll();
}
