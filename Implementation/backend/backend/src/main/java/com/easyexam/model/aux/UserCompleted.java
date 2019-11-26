package com.easyexam.model.aux;

public class UserCompleted {

    private int id;
    private int bonus;
    private String email;

    private int count;
    private int cantidad;

    private String first_name;
    private String last_name;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public UserCompleted() { }

    public UserCompleted(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public UserCompleted(int id, int bonus, String email, int count, int cantidad, String first_name, String last_name) {
        this.id = id;
        this.bonus = bonus;
        this.email = email;
        this.count = count;
        this.cantidad = cantidad;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public UserCompleted(int id, String email, int count, String first_name, String last_name) {
        this.id = id;
        this.email = email;
        this.count = count;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
