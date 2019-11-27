package com.easyexam.repository;

import com.easyexam.model.aux.PasswordResetToken;
import com.easyexam.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPasswordResetRepo extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);

}