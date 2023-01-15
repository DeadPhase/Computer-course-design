package com.example.zheng.pojo;

//购物车
public class ShopCart {
    private String scId;        //购物车主键
    private String usercount;      //购物车主人
    private String bookName;    //书名
    private int count;          //数量
    private  Double price;      //价格
    private String store;       //店铺
    private int is_buyshop;

    public ShopCart() {

    }


    public ShopCart(String scId, String usercount, String bookName, int count, Double price, String store,int is_buyshop) {
        this.scId = scId;
        this.usercount = usercount;
        this.bookName = bookName;
        this.count = count;
        this.price = price;
        this.store = store;
        this.is_buyshop = is_buyshop;
    }

    public int getIs_buyshop() {
        return is_buyshop;
    }

    public void setIs_buyshop(int is_buyshop) {
        this.is_buyshop = is_buyshop;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getUsercount() {
        return usercount;
    }

    public void setUsercount(String usercount) {
        this.usercount = usercount;
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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
