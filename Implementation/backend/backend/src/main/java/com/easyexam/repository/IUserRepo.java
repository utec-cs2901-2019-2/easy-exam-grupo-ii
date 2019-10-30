package com.easyexam.repository;

import com.easyexam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Integer>{
    User findAllByEmail(String email);
}
