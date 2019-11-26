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

        String documentClass = "\\documentclass[11pt,addpoints]{exam} \n";
        String usePackage = "\\usepackage{amsfonts,amssymb,amsmath, amsthm} \n";
        String usePackage2 = "\\usepackage{graphicx} \n";
        String usePackage3 = "\\usepackage{systeme} \n";
        String usePackage4 = "\\usepackage{pgf,tikz,pgfplots} \n";
        String usePackage5 = "\\pgfplotsset{compat=1.15} \n";
        String usePackage6 = "\\usepgfplotslibrary{fillbetween} \n";
        String usePackage7 = "\\usepackage{mathrsfs} \n";
        String usePackage8 = "\\usetikzlibrary{arrows} \n";
        String usePackage9 = "\\usetikzlibrary{calc} \n";

        String pageStyle = "\\pagestyle{headandfoot} \n";
        String pageHeader = "\\firstpageheader{";
        String points = " (\\numpoints\\ points) \\newline ";
        String today = " \\newline \\today }{}{Name: \\underline{\\hspace{2.5in}}} \n";


        String beginDocument = "\\begin{document} \n";
        String center = "\\begin{center} \n";
        String prevIndications = "\\fbox{\\fbox{\\parbox{6in}{\\centering \\textbf{Indications: } \n";
        String endCenter = "\\end{center} \n";
        String beginQuestion = "\\begin{questions} \n";
        String prevQuestion = "\\question[";
        String endSqrBracket = "] \t";
        String label = "\\label{DisplayModeExample} \n";

        String subsection = "\\subsection*{";
        String endQuestion = "\\end{questions} \n";
        String endBracket=" } \n";
        String endDocument = "\\end{document} \n";

        String path="./src/main/java/com/easyexam/files";
        String separator="/";

        List<ExamProblem> examP=examProblemRepo.findAllByExamProblemId_IdExam(idExam);

        FileOutputStream out = new FileOutputStream(path+separator+name);

        out.write(documentClass.getBytes());
        out.write(usePackage.getBytes());
        out.write(usePackage2.getBytes());
        out.write(usePackage3.getBytes());
        out.write(usePackage4.getBytes());
        out.write(usePackage5.getBytes());
        out.write(usePackage6.getBytes());
        out.write(usePackage7.getBytes());
        out.write(usePackage8.getBytes());
        out.write(usePackage9.getBytes());

        out.write(pageStyle.getBytes());
        String header = pageHeader + e.getTitle() + points + e.getCourse()+today;
        out.write(header.getBytes());
        out.write(beginDocument.getBytes());

        String author="\\textbf{Professor: }" + t.getFirstname() + " "+t.getLastname()+" \\newline \n";
        out.write(author.getBytes());
        String duration="\\textbf{Duration: }" + e.getDuration() + " \\newline \n";
        out.write(duration.getBytes());

        out.write(center.getBytes());
        String indications = prevIndications +  e.getIndications() + endBracket + endBracket + endBracket;
        out.write(indications.getBytes());
        out.write(endCenter.getBytes());

        out.write(beginQuestion.getBytes());

        for(ExamProblem ep:examP){
            String question = prevQuestion+Integer.toString(ep.getScore())+endSqrBracket+label;
            out.write(question.getBytes());
            Problem p=problemRepo.findProblemById(ep.getExamProblemId().getIdProblem());
            String pTitle = subsection + p.getTitle() + endBracket;
            out.write(pTitle.getBytes());
            String body = p.getBody() + " \n";
            out.write(body.getBytes());

        }
        out.write(endQuestion.getBytes());
        out.write(endDocument.getBytes());
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
