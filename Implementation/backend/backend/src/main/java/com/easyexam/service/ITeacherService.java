package com.easyexam.service;

import com.easyexam.model.Teacher;
import java.util.List;


public interface ITeacherService {

    Teacher save(Teacher teacher);

    Teacher update(Teacher item);

    void delete(int id);

    List<Teacher> findAll();

    Teacher findOne(int id);

}
