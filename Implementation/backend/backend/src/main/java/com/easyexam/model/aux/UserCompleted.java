package com.easyexam.model.aux;

public class UserCompleted {

    private int id;
    private int bonus;
    private String email;

    public UserCompleted() { }

    public UserCompleted(int id, int bonus, String email) {
        this.id = id;
        this.bonus = bonus;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
