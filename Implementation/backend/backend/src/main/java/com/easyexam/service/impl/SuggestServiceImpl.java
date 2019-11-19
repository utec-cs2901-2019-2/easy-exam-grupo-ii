package com.easyexam.service.impl;

import com.easyexam.repository.ISuggestRepo;
import com.easyexam.service.ISuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SuggestServiceImpl implements ISuggestService {

    @Autowired
    ISuggestRepo suggestRepo;

    @Transactional
    public int reportProblem(String comment, int idProblem, int idTeacher){
        return suggestRepo.reportProblem(comment, idProblem, idTeacher);
    }

}
