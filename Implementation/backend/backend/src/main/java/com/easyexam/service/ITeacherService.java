package com.easyexam.service;

import com.easyexam.model.Teacher;
import com.easyexam.repository.ITeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class ITeacherService {

    @Autowired
    private ITeacherRepo teacherRepo;

    public Teacher save(Teacher teacher){
        Teacher newTeacher = new Teacher();
        newTeacher.setFirstname(teacher.getFirstname());
        newTeacher.setLastname(teacher.getLastname());
        newTeacher.setCountry(teacher.getCountry());
        newTeacher.setDate_birth(teacher.getDate_birth());
        newTeacher.setUser(teacher.getUser());
        return teacherRepo.save(newTeacher);
    }

    public Teacher update(Teacher item){
        return teacherRepo.save(item);
    }

    public void delete(int id){
        teacherRepo.delete(findOne(id));
    }

    public List<Teacher> findAll(){
        List<Teacher> items = new ArrayList<>();

        for (Teacher item :teacherRepo.findAll()) {
            items.add(item);
        }
        return items;
    }

    public Teacher findOne(int id){
        return teacherRepo.findById(id).get();
    }
}
