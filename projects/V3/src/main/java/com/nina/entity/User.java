package com.nina.entity;

public class User {
    private Integer userid;

    private String username;

    private String password;

    private String gender;

    private String position;

    private String tele;

    private String email;

    public User() {
    }

    public User(String username, String password, String gender, String position, String tele, String email) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.position = position;
        this.tele = tele;
        this.email = email;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}