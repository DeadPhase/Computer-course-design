package com.example.zheng.pojo;

public class Comment {
    //购买商品品论表
    private String cId;
    private String discuss;
    private String usercount;
    private String bookId;
    private String times;

    public Comment() {  }

    public Comment(String cId, String discuss, String usercount,String bookId,String times) {
        this.cId = cId;
        this.discuss = discuss;
        this.usercount = usercount;
        this.bookId = bookId;
        this.times = times;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getDiscuss() {
        return discuss;
    }

    public void setDiscuss(String discuss) {
        this.discuss = discuss;
    }

    public String getUsercount() {
        return usercount;
    }

    public void setUsercount(String usercount) {
        this.usercount = usercount;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
