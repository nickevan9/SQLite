package com.example.sqlite.model;

public class Product {
    private String productID;
    private String productName;
    private int count;
    private int price;
    private String productType;
    private String date;

    public Product(String productID, String productName, int count, int price, String productType, String date) {
        this.productID = productID;
        this.productName = productName;
        this.count = count;
        this.price = price;
        this.productType = productType;
        this.date = date;
    }

    public Product() {
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
