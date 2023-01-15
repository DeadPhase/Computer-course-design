package com.example.zheng.pojo;

public class Order_address {
    private String oaId;
    private String usId;
    private String haId;

    public Order_address() {
    }

    public Order_address(String oaId, String usId, String haId) {
        this.oaId = oaId;
        this.usId = usId;
        this.haId = haId;
    }

    public String getOaId() {
        return oaId;
    }

    public void setOaId(String oaId) {
        this.oaId = oaId;
    }

    public String getUsId() {
        return usId;
    }

    public void setUsId(String usId) {
        this.usId = usId;
    }

    public String getHaId() {
        return haId;
    }

    public void setHaId(String haId) {
        this.haId = haId;
    }
}
