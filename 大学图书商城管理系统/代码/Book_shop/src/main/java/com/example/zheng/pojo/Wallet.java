package com.example.zheng.pojo;

public class Wallet {
    private String wId;
    private Double balance;
    private String usercount;

    public Wallet() {

    }

    public Wallet(String wId, Double balance, String usercount) {
        this.wId = wId;
        this.balance = balance;
        this.usercount = usercount;
    }

    public String getwId() {
        return wId;
    }

    public void setwId(String wId) {
        this.wId = wId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getUsercount() {
        return usercount;
    }

    public void setUsercount(String usercount) {
        this.usercount = usercount;
    }
}
