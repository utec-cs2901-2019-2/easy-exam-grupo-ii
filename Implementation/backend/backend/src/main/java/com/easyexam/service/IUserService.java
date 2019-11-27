package com.easyexam.service;

import com.easyexam.model.User;
import com.easyexam.model.aux.UserCompleted;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {

    UserDetails loadUserByUsername(String username);

    void delete(int id);

    User update(User user);

    User save(User user);

    List<User> findAll();

    User findUser(String email, String passwd);

    User findByEmail(String username);

    User findById(int id);

    User updatePassword(int id);

    UserCompleted getDates(String email);

    List<UserCompleted> getReportedUsers();

    void deleteSuggestById(int id);
}
