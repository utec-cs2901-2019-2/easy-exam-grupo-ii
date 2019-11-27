package com.easyexam.service;

import com.easyexam.model.Exam;
import com.easyexam.model.aux.ExamCompleted;

import java.util.List;

public interface IExamService {

    int saveExam(ExamCompleted exam);

    List<Exam> getExamsByTeacher(int idTeacher);
}
