package com.easyexam.service;

import com.easyexam.model.Teacher;
import com.easyexam.repository.ITeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherService {

    @Autowired
    private ITeacherRepo teacherRepo;

    public Teacher save(Teacher teacher){
        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(teacher.getFirstName());
        newTeacher.setLastName(teacher.getLastName());
        newTeacher.setCountry(teacher.getCountry());
        newTeacher.setBirtday(teacher.getBirthday());
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
