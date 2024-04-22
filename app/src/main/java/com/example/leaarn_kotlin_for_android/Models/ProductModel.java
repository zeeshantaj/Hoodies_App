package com.example.leaarn_kotlin_for_android.Models;

public class ProductModel {
    private String name,storeName,productImageUrl;
    private int rating,productPrice;

    public ProductModel(String name, String storeName, String productImageUrl, int rating, int productPrice) {
        this.name = name;
        this.storeName = storeName;
        this.productImageUrl = productImageUrl;
        this.rating = rating;
        this.productPrice = productPrice;
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

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
