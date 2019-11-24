package com.easyexam.service.implOthers;

import java.io.IOException;

public interface IFileService {

    void generateLatex(String name,int idExam) throws IOException;

    void generatePDF(String name,String fileLatex) throws IOException;
}
