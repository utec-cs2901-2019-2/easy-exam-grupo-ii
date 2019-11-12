package com.easyexam.model.aux;

public class AuthToken {

    private String token;
    private String username;
    private int credits;

    public AuthToken(){

    }

    public AuthToken(String token, String username, int credits){
        this.token = token;
        this.username = username;
        this.credits = credits;
    }

    public AuthToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
