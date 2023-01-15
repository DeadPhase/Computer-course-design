package com.example.zheng.pojo;

public class ShopCartList {
    private String csId;
    private String slId;
    private String scId;

    public ShopCartList() {

    }

    public ShopCartList(String csId, String slId, String scId) {
        this.csId = csId;
        this.slId = slId;
        this.scId = scId;
    }

    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }
}
