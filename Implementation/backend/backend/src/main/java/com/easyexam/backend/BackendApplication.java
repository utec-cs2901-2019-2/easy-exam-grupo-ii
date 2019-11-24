package com.easyexam.backend;

import com.easyexam.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"com.easyexam.controller","com.easyexam.config","com.easyexam.service","com.easyexam.service.impl"})
@EntityScan("com.easyexam.model")
@EnableJpaRepositories("com.easyexam.repository")
public class BackendApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		
	}

}
