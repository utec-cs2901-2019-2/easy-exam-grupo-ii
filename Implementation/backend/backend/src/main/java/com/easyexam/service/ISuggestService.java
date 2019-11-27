package com.easyexam.service;

import com.easyexam.model.*;
import java.util.List;


public interface ISuggestService {
    
    
    int reportProblem(String comment, int idProb, int idTeacher);

    Boolean findReport(int idProblem,int idTeacher);

}
