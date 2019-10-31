package com.easyexam.model;



import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "user_role")
@SequenceGenerator(name="seq_usu", sequenceName="seq_usu")  
public class User {

    @Id
    @GeneratedValue(generator="seq_usu")
    private int id;

    @Email
    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="activate")
    private Boolean activate;

    @Column(name="FirstName")
    private String firstName;

    @Column(name="LastName")
    private String lastName;

    @Column(name="Country")
    private String country;

    @Column(name="BirthDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime birthday;


    public User() { }

    public User(String email, String password, Boolean activate, String
    firstName, String lastName, String Country, LocalDateTime birthday) {
        this.email = email;
        this.password = password;
        this.activate = activate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivate() {
        return activate;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setlastName(String lastName){
        this.lastName = lastName;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public LocalDateTime getBirthday(){
        return birthday;
    }

    public void LocalDateTime setBirtday(LocalDateTime birthday){
        this.birthday = birthday;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }
}
