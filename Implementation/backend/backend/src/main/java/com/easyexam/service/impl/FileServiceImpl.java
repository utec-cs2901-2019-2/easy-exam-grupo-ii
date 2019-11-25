package com.easyexam.service.impl;

import com.easyexam.controller.ExamController;
import com.easyexam.model.*;
import com.easyexam.repository.*;
import com.easyexam.service.implOthers.IFileService;
import de.nixosoft.jlr.JLRGenerator;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements IFileService {

    private static Logger LOG= LoggerFactory.getLogger(ExamController.class);

    @Autowired
    IExamTeacherRepo examTeacherRepo;

    @Autowired
    IExamProblemRepo examProblemRepo;

    @Autowired
    IProblemRepo problemRepo;

    @Autowired
    IExamRepo examRepo;

    @Autowired
    ITeacherRepo teacherRepo;

    public void generateLatex(String name,int idExam,int idTeacher) throws IOException {
        LOG.info("entro a generate Latex");
        Exam e=examRepo.findExamById(idExam);

        Teacher t=teacherRepo.findTeacherById(idTeacher);

        String documentclass = "\\documentclass{article} \n";
        String title = "\\title{";
        String begin = "\\begin{document} \n";
        String maketitle = "\\maketitle \n";

        String section = "\\section{ ";
        String endBracket=" } \n";
        String subsection = "\\subsection{SubProblem} \n";
        String end = "\\end{document} \n";
        String endl=" \\newline \n";

        String path="./src/main/java/com/easyexam/files";
        String separator="/";

        List<ExamProblem> examP=examProblemRepo.findAllByExamProblemId_IdExam(idExam);

        FileOutputStream out = new FileOutputStream(path+separator+name);

        out.write(documentclass.getBytes());
        title=title+e.getTitle()+endBracket;
        out.write(title.getBytes());
        out.write(begin.getBytes());
        out.write(maketitle.getBytes());


        String author="Author: "+t.getFirstname()+ " "+t.getLastname()+endl;
        out.write(author.getBytes());


        String courseENdl="Course : "+e.getCourse()+"$                                              $ Duration: "+e.getDuration()+endl;
        out.write(courseENdl.getBytes());

        String indicaciontTitle="Indicactions: \n"+endl;
        out.write(indicaciontTitle.getBytes());

        String indicationEndl=e.getIndications()+endl;
        out.write(indicationEndl.getBytes());

        String nameStudent="Name: "+endl;
        out.write(nameStudent.getBytes());

        for(ExamProblem ep:examP){
            Problem p=problemRepo.findProblemById(ep.getExamProblemId().getIdProblem());
            String tmp=section+p.getTitle()+" $("+Integer.toString(ep.getScore())+" points)$"+endBracket;
            out.write(tmp.getBytes());
            out.write(p.getBody().getBytes());
        }

        out.write(end.getBytes());
        out.close();
        LOG.info("acabo a generate Latex");

    }

    public void generatePDF(String name,String fileLatex) throws IOException {

        String path="./src/main/java/com/easyexam/files";
        String separator="/";


        File workingDirectory = new File(path);

        File template = new File(workingDirectory.getAbsolutePath() + separator + fileLatex);

        JLRGenerator pdfGen = new JLRGenerator();
        pdfGen.deleteTempFiles(false,false,false);

        if (!pdfGen.generate(template, workingDirectory, workingDirectory)) {
            System.out.println(pdfGen.getErrorMessage());
        }
        //JLROpener.open(pdfGen.getPDF());

    }

}
