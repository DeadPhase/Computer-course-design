package com.example.zheng.pojo;

public class Book_cart_relation {
    private String bcrId;
    private String bookId;
    private String scId;

    public Book_cart_relation() {
    }

    public Book_cart_relation(String bcrId, String bookId, String scId) {
        this.bcrId = bcrId;
        this.bookId = bookId;
        this.scId = scId;
    }

    public String getBcrId() {
        return bcrId;
    }

    public void setBcrId(String bcrId) {
        this.bcrId = bcrId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }
}
