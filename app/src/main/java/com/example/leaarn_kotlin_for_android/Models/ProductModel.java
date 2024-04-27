package com.example.leaarn_kotlin_for_android.Models;

public class ProductModel {
    private String productName,storeName;
    //private String productImageUrl;
    private int productImg1;
    private float rating;
    private double price;

    public ProductModel() {
    }

    public ProductModel(String name, String storeName, float rating, double productPrice, int productImg) {
        this.productName = name;
        this.storeName = storeName;
        this.rating = rating;
        this.price = productPrice;
        this.productImg1 = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductImg1() {
        return productImg1;
    }

    public void setProductImg1(int productImg1) {
        this.productImg1 = productImg1;
    }
}
