package com.example.zheng.pojo;

public class UserShopRelation {
    private String usrId;
    private String usercount;
    private String slId;

    public UserShopRelation() {

    }

    public UserShopRelation(String usrId, String usercount, String slId) {
        this.usrId = usrId;
        this.usercount = usercount;
        this.slId = slId;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getUsercount() {
        return usercount;
    }

    public void setUsercount(String usercount) {
        this.usercount = usercount;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }
}
