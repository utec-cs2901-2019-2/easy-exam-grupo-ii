package com.easyexam.model;

import javax.persistence.*;

@Entity
@Table(name="role")
@SequenceGenerator(name = "seq_role",sequenceName = "seqRole")
public class Role {

    @Id
    @GeneratedValue(generator = "seq_role")
    private int id;

    @Column(name="name")
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
