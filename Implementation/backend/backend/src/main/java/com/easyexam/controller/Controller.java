package com.easyexam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/")
    String hello(){
        return "Hola Mundo";
    }

    @RequestMapping("/greeting")
    String into_greeting(){
        return "Into Greeting";
    }
    @GetMapping("/hey")
    String hey(){
        return "hey";
    }
}
