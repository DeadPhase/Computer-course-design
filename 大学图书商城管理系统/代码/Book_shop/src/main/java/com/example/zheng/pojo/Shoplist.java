package com.example.zheng.pojo;


//临时购物清单表
public class Shoplist {
   private String slId;
    private String bookName;
    private int count;
    private Double price;
    private int is_buy;



    public Shoplist() {

    }

    public Shoplist(String slId, String bookName, int count, Double price,int is_buy) {
        this.slId = slId;
        this.bookName = bookName;
        this.count = count;
        this.price = price;
        this.is_buy = is_buy;
    }

    public int getIs_buy() {
        return is_buy;
    }

    public void setIs_buy(int is_buy) {
        this.is_buy = is_buy;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
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
}
