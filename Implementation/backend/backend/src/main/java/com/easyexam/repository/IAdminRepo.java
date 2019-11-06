package com.easyexam.repository;

import com.easyexam.model.Admin;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IAdminRepo extends JpaRepository<Admin, Integer> {
}
