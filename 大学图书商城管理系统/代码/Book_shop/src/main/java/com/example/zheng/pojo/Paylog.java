package com.example.zheng.pojo;

public class Paylog {
    private String pId;
    private String usercount;
    private int money;
    private String pay_time;

    public Paylog() {

    }

    public Paylog(String pId, String usercount, int money, String pay_time) {
        this.pId = pId;
        this.usercount = usercount;
        this.money = money;
        this.pay_time = pay_time;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getUsercount() {
        return usercount;
    }

    public void setUsercount(String usercount) {
        this.usercount = usercount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }
}
