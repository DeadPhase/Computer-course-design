package com.example.zheng.pojo;

public class Authority {
    private String ahId;
    private String authName;

    public Authority() {

    }

    public Authority(String ahId, String authName) {
        this.ahId = ahId;
        this.authName = authName;
    }

    public String getAhId() {
        return ahId;
    }

    public void setAhId(String ahId) {
        this.ahId = ahId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }
}
