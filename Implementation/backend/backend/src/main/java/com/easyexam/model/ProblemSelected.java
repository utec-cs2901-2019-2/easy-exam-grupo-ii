package com.easyexam.model;

import javax.persistence.*;

@Entity
@Table(name="problem_selected")
@SequenceGenerator(sequenceName = "seqProbSel",name="seq_problem_selected")
public class ProblemSelected {

    @Id
    @GeneratedValue(generator = "seq_problem_selected")
    private int id;


}
