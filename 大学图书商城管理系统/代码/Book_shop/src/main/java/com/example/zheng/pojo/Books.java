package com.example.zheng.pojo;

public class Books {
    private String bookId;
    private String bookName;
    private String bookAuthor;
    private Double price;
    private String address;
    private String impression;
    private String introduce;

    private String bkId;

    //新增这两个字段的原因，将前台传入的价格范围封装、在编写sql语句的时候动态替换价格参数
    private Double minPrice;//最小价格
    private Double maxPrice;//最大价格

    public Books(String bookId, String bookName, String bookAuthor, Double price, String address, String impression, String introduce) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.price = price;
        this.address = address;
        this.impression = impression;
        this.introduce = introduce;
    }

    public Books(String bookId, String bookName, String bookAuthor, Double price, String address, String impression, String introduce, Double minPrice, Double maxPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.price = price;
        this.address = address;
        this.impression = impression;
        this.introduce = introduce;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String getBkId() {
        return bkId;
    }

    public void setBkId(String bkId) {
        this.bkId = bkId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Books() { }


    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
