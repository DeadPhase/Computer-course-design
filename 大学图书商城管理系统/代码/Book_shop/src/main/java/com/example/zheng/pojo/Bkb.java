package com.example.zheng.pojo;

//书籍种类和书籍信息关联表

public class Bkb {
    private String bbId;
    private String bkId;
    private String bookId;

    public Bkb() {

    }
    public Bkb(String bbId, String bkId, String bookId) {
        this.bbId = bbId;
        this.bkId = bkId;
        this.bookId = bookId;
    }

    public String getBbId() {
        return bbId;
    }

    public void setBbId(String bbId) {
        this.bbId = bbId;
    }

    public String getBkId() {
        return bkId;
    }

    public void setBkId(String bkId) {
        this.bkId = bkId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
