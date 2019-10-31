package com.easyexam.model;

import javax.persistence.*;

@Entity
@Table (name = "comment")
@SequenceGenerator(name="seq_usu", sequenceName="seq_usu")
public class Comment {

    @Id
    @GeneratedValue(generator="seq_usu")
    private int id;

    //private Problem problem;
    //private User user;
    //private String description;


}
