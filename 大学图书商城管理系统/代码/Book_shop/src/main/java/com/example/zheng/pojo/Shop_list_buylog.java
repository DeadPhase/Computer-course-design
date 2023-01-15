package com.example.zheng.pojo;

public class Shop_list_buylog {
    private String slbId;
    private String slId;
    private String bgId;

    public Shop_list_buylog() {

    }

    public Shop_list_buylog(String slbId, String slId, String bgId) {
        this.slbId = slbId;
        this.slId = slId;
        this.bgId = bgId;
    }

    public String getSlbId() {
        return slbId;
    }

    public void setSlbId(String slbId) {
        this.slbId = slbId;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }

    public String getBgId() {
        return bgId;
    }

    public void setBgId(String bgId) {
        this.bgId = bgId;
    }
}
