package com.easyexam.model;

import javax.persistence.Entity;

@Entity
public class Comment {

    private Problem problem;
    private User user;
    private String description;


}
