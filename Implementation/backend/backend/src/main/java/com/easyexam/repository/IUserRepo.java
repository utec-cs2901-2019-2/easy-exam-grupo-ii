package com.easyexam.repository;

import com.easyexam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {

    User findUserByEmail(String email);
    User findUserByEmailAndPassword(String email, String passwd);
    User findUserById(int id);
}
