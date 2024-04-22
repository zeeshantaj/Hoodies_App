package com.example.leaarn_kotlin_for_android.Models;

public class ProductModel {
    private String name,storeName;
    //private String productImageUrl;
    private int productImg;
    private float rating;
    private double productPrice;

    public ProductModel(String name, String storeName, float rating, double productPrice, int productImg) {
        this.name = name;
        this.storeName = storeName;
        this.rating = rating;
        this.productPrice = productPrice;
        this.productImg = productImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductImg() {
        return productImg;
    }

    public void setProductImg(int productImg) {
        this.productImg = productImg;
    }
}
