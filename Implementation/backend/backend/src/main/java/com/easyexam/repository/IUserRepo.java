package com.easyexam.repository;

import com.easyexam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Integer>{
    User findUserByEmail(String email);
    User findUserByEmailAndPassword(String email, String passwd);
}
