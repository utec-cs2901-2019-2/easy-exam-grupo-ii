package com.easyexam.service;

import com.easyexam.model.Teacher;
import com.easyexam.model.User;
import com.easyexam.model.aux.UserCompleted;

import java.util.List;


public interface ITeacherService {

    Teacher save(Teacher teacher);

    Teacher update(Teacher item);

    void delete(int id);

    List<Teacher> findAll();

    Teacher findOne(int id);

    Teacher findOneByUser(User user);

    Boolean updateBonus(int bonus,int idUser);
}
