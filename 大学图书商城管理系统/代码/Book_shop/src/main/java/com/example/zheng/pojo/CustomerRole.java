package com.example.zheng.pojo;


//用户和角色联合表
public class CustomerRole {
    private String crId;
    private String usercount;
    private String roleId;

    public CustomerRole() {
    }

    public CustomerRole(String crId, String usercount, String roleId) {
        this.crId = crId;
        this.usercount = usercount;
        this.roleId = roleId;
    }

    public String getCrId() {
        return crId;
    }

    public void setCrId(String crId) {
        this.crId = crId;
    }

    public String getUsercount() {
        return usercount;
    }

    public void setUsercount(String usercount) {
        this.usercount = usercount;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
