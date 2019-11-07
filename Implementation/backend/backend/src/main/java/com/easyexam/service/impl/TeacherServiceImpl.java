package com.easyexam.service.impl;

import com.easyexam.repository.IExamTeacherRepo;
import com.easyexam.repository.ITeacherRepo;
import com.easyexam.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    ITeacherRepo teacherRepo;

    @Autowired
    IExamTeacherRepo examTeacherRepo;


}
