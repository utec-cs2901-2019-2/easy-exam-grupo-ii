package com.easyexam.service.impl;

import com.easyexam.model.Teacher;
import com.easyexam.model.User;
import com.easyexam.model.aux.UserCompleted;
import com.easyexam.repository.IExamTeacherRepo;
import com.easyexam.repository.ITeacherRepo;
import com.easyexam.repository.IUserRepo;
import com.easyexam.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    ITeacherRepo teacherRepo;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IExamTeacherRepo examTeacherRepo;


    @Override
    public Teacher save(Teacher teacher) {
        Teacher newTeacher = new Teacher();
        newTeacher.setFirstname(teacher.getFirstname());
        newTeacher.setLastname(teacher.getLastname());
        newTeacher.setCountry(teacher.getCountry());
        newTeacher.setDate_birth(teacher.getDate_birth());
        newTeacher.setUser(teacher.getUser());
        newTeacher.setBonus(teacher.getBonus());
        newTeacher.setGender(teacher.getGender());
        newTeacher.setInstitution(teacher.getInstitution());
        return teacherRepo.save(newTeacher);
    }

    @Override
    public Teacher update(Teacher item) {
        return teacherRepo.save(item);
    }

    @Override
    public void delete(int id) {
        teacherRepo.delete(findOne(id));
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> items = new ArrayList<>();

        for (Teacher item :teacherRepo.findAll()) {
            items.add(item);
        }
        return items;
    }

    @Override
    public Teacher findOne(int id) {
        return teacherRepo.findTeacherById(id);
    }

    public Teacher findOneByUser(User user){
        return teacherRepo.findByUser(user);
    }



    @Override
    public Boolean updateBonus(int bonus, int idUser) {
        teacherRepo.updateBonus(bonus,idUser);
        return true;
    }
}
