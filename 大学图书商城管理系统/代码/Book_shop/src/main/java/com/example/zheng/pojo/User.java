package com.example.zheng.pojo;

public class User {
    private String name;
    private String password;
    private String term;

    public User() {

    }

    public User(String name, String password, String term) {
        this.name = name;
        this.password = password;
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
