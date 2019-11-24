package com.easyexam.controller;

import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.ExamCompleted;
import com.easyexam.service.IExamService;
import com.easyexam.service.impl.ProblemServiceImpl;
import de.nixosoft.jlr.JLRConverter;
import de.nixosoft.jlr.JLRGenerator;
import de.nixosoft.jlr.JLROpener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/exam/v1")
public class ExamController {


    private static Logger LOG= LoggerFactory.getLogger(ExamController.class);

    @Autowired
    IExamService examService;

    String path="./src/main/java/com/easyexam/files";
    String separator="/";

    @PostMapping("/submitExam")
    public ApiResponse<ExamCompleted> register(@Valid @RequestBody ExamCompleted examCompleted) {
        LOG.info("Entro a submit problem");
        LOG.info(examCompleted.getTitle());
        examService.saveExam(examCompleted);
        return new ApiResponse<>(200, "success", true);
    }

    @GetMapping("/generateExam")
    public void generateExam(HttpServletResponse response) throws IOException {
        LOG.info("Entro a generate Exam");
        String name="prueba.tex";
        String namePDF="prueba.pdf";
        generateLatex(name);
        generatePDF(namePDF,name);
        response.setContentType("application/pdf");

        response.setHeader("Content-Disposition","attachment;filename="+path+separator+"prueba.pdf");
        response.setHeader("Content-Transfer-Enconding","binary");

        try{
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fis = new FileInputStream(path+separator+"prueba.pdf");
            int len;
            byte[] buf = new byte[1024];
            while((len = fis.read(buf)) > 0) {
                bos.write(buf,0,len);
            }
            bos.close();
            response.flushBuffer();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    private void generateLatex(String name) throws IOException {
        LOG.info("entro a generate Latex");
        String documentclass = "\\documentclass{article} \n";
        String title = "\\title{NewTitle} \n";
        String begin = "\\begin{document} \n";
        String maketitle = "\\maketitle \n";
        String problem = "Helloworld \n";
        String end = "\\end{document} \n";

        String path="./src/main/java/com/easyexam/files";
        String separator="/";

        FileOutputStream out = new FileOutputStream(path+separator+name);

        out.write(documentclass.getBytes());
        out.write(title.getBytes());
        out.write(begin.getBytes());
        out.write(maketitle.getBytes());
        out.write(problem.getBytes());
        out.write(end.getBytes());
        out.close();
        LOG.info("acabo a generate Latex");
    }

    private void generatePDF(String name,String fileLatex) throws IOException {

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
