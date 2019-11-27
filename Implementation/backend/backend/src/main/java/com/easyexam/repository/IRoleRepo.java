package com.easyexam.repository;

import com.easyexam.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepo extends JpaRepository<Role,Integer> {
    @Override
    List<Role> findAll();
}
