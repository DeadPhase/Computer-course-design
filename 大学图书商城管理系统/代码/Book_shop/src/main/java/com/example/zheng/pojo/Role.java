package com.example.zheng.pojo;

public class Role {
    private String roleId;
    private String rname;

    public Role() {

    }

    public Role(String roleId, String rname) {
        this.roleId = roleId;
        this.rname = rname;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
