package com.example.zheng.pojo;

public class UserShopList {
    /**
     * 待付款：is_pay  0 :代表未付款；1：代表付款
     * 待发货：is_fahuo  0：代表未发货：1：代表发货
     * 待收货：is_gain    0：代表未收货  1：代表收货
     * 待评论：is_comment  0：代表未评论  1：代表品论
     */

    private String usId;
    private String usercount;
    private int is_status;
    private String is_time;
    private int is_pay;
    private int is_fahuo;
    private int is_gain;
    private int is_comment;

    private int is_all;

    public UserShopList() { }

    public UserShopList(String usId, String usercount, int is_status, String is_time, int is_pay, int is_fahuo, int is_gain, int is_comment,int is_all) {
        this.usId = usId;
        this.usercount = usercount;
        this.is_status = is_status;
        this.is_time = is_time;
        this.is_pay = is_pay;
        this.is_fahuo = is_fahuo;
        this.is_gain = is_gain;
        this.is_comment = is_comment;
        this.is_all = is_all;
    }

    public UserShopList(String usId, String usercount, int is_status, String is_time) {
        this.usId = usId;
        this.usercount = usercount;
        this.is_status = is_status;
        this.is_time=is_time;
    }

    public int getIs_all() {
        return is_all;
    }

    public void setIs_all(int is_all) {
        this.is_all = is_all;
    }

    public int getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(int is_pay) {
        this.is_pay = is_pay;
    }

    public int getIs_fahuo() {
        return is_fahuo;
    }

    public void setIs_fahuo(int is_fahuo) {
        this.is_fahuo = is_fahuo;
    }

    public int getIs_gain() {
        return is_gain;
    }

    public void setIs_gain(int is_gain) {
        this.is_gain = is_gain;
    }

    public int getIs_comment() {
        return is_comment;
    }

    public void setIs_comment(int is_comment) {
        this.is_comment = is_comment;
    }

    public String getIs_time() {
        return is_time;
    }

    public void setIs_time(String is_time) {
        this.is_time = is_time;
    }

    public String getUsId() {
        return usId;
    }

    public void setUsId(String usId) {
        this.usId = usId;
    }

    public String getUsercount() {
        return usercount;
    }

    public void setUsercount(String usercount) {
        this.usercount = usercount;
    }

    public int getIs_status() {
        return is_status;
    }

    public void setIs_status(int is_status) {
        this.is_status = is_status;
    }
}
