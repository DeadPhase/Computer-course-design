package com.example.zheng.pojo;


public class Hvaddress {
    private String haId;
    private String usercount;
    private String name;
    private String address;
    private String tel;

    public Hvaddress() {

    }

    public Hvaddress(String haId,String usercount, String name, String address, String tel) {
        this.haId = haId;
        this.usercount = usercount;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public String getUsercount() {
        return usercount;
    }

    public void setUsercount(String usercount) {
        this.usercount = usercount;
    }

    public String getHaId() {
        return haId;
    }

    public void setHaId(String haId) {
        this.haId = haId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
