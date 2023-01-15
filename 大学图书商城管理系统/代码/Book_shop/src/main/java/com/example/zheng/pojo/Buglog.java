package com.example.zheng.pojo;

public class Buglog {
    private String bgId;
    private String bookName;
    private int count;
    private Double price;
    private String usId;

    public Buglog() { }

    public Buglog(String bgId, String bookName, int count, Double price, String usId) {
        this.bgId = bgId;
        this.bookName = bookName;
        this.count = count;
        this.price = price;
        this.usId = usId;
    }

    public String getBgId() {
        return bgId;
    }

    public void setBgId(String bgId) {
        this.bgId = bgId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUsId() {
        return usId;
    }

    public void setUsId(String usId) {
        this.usId = usId;
    }
}
