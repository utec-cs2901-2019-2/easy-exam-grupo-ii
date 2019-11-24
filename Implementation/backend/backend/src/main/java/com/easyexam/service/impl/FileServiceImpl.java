package com.easyexam.service.impl;

import com.easyexam.controller.ExamController;
import com.easyexam.model.Exam;
import com.easyexam.model.ExamProblem;
import com.easyexam.model.Problem;
import com.easyexam.repository.IExamProblemRepo;
import com.easyexam.repository.IExamRepo;
import com.easyexam.repository.IExamTeacherRepo;
import com.easyexam.repository.IProblemRepo;
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

    public void generateLatex(String name,int idExam) throws IOException {
        LOG.info("entro a generate Latex");

        String documentclass = "\\documentclass{article} \n";
        String title = "\\title{NewTitle} \n";
        String begin = "\\begin{document} \n";
        String maketitle = "\\maketitle \n";
        //String problem = "Helloworld \n";

        String section = "\\section{ ";
        String endBracket=" } \n";
        String subsection = "\\subsection{SubProblem} \n";
        String end = "\\end{document} \n";
        String endl="\n";

        String path="./src/main/java/com/easyexam/files";
        String separator="/";

        List<ExamProblem> examP=examProblemRepo.findAllByExamProblemId_IdExam(idExam);

        FileOutputStream out = new FileOutputStream(path+separator+name);

        out.write(documentclass.getBytes());
        out.write(title.getBytes());
        out.write(begin.getBytes());
        out.write(maketitle.getBytes());

        Exam e=examRepo.findExamById(idExam);

        String courseENdl="Course : "+e.getCourse()+endl;
        out.write(courseENdl.getBytes());

        String indicaciontTitle="Indicactions: \n";
        out.write(indicaciontTitle.getBytes());

        String indicationEndl=e.getIndications()+endl;
        out.write(indicationEndl.getBytes());

        for(ExamProblem ep:examP){
            Problem p=problemRepo.findProblemById(ep.getExamProblemId().getIdProblem());
            String tmp=section+p.getTitle()+ep.getScore()+endBracket;
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
