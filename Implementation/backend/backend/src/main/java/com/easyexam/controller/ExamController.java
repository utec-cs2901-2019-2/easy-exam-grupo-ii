package com.easyexam.controller;

import com.easyexam.model.Exam;
import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.ExamCompleted;
import com.easyexam.service.IExamService;
import com.easyexam.service.impl.ProblemServiceImpl;
import com.easyexam.service.implOthers.IFileService;
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
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/exam/v1")
public class ExamController {


    private static Logger LOG= LoggerFactory.getLogger(ExamController.class);

    @Autowired
    IExamService examService;

    @Autowired
    IFileService fileService;

    String path="./src/main/java/com/easyexam/files";
    String separator="/";

    @PostMapping("/submitExam")
    public ApiResponse<ExamCompleted> register(@Valid @RequestBody ExamCompleted examCompleted) {
        LOG.info("Entro a submit problem");
        LOG.info(examCompleted.getTitle());
        int idExam=examService.saveExam(examCompleted);

        return new ApiResponse<>(200, "success", idExam);
    }

    @GetMapping("/generateExam")
    public void generateExam(int idExam,int idTeacher,HttpServletResponse response) throws IOException {
        LOG.info("Entro a generate Exam");
        LOG.info(Integer.toString(idExam));
        String name="prueba.tex";
        String namePDF="prueba.pdf";
        fileService.generateLatex(name,idExam,idTeacher);
        fileService.generatePDF(namePDF,name);

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

    @GetMapping("/generateExamLatex")
    public void generateExam(HttpServletResponse response) throws IOException {
        LOG.info("Entro a generate Exam");

        response.setContentType("application/tex");

        response.setHeader("Content-Disposition","attachment;filename="+path+separator+"prueba.tex");
        response.setHeader("Content-Transfer-Enconding","binary");

        try{
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fis = new FileInputStream(path+separator+"prueba.tex");
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

    @GetMapping("/exam/getExams")
    public List<Exam> getExams(int idTeacher) throws AuthenticationException{
        return examService.getExamsByTeacher(idTeacher);
    }


}
