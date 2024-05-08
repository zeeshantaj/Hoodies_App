package com.example.leaarn_kotlin_for_android.Models;

public class ProductModel {

    private String productName,storeName,productId,size;
    //private String productImageUrl;
    private String productImage;
    private double rating;
    private double price;

    public ProductModel() {
    }


    public ProductModel(String name, String storeName, double rating, double productPrice, String productImg) {
        this.productName = name;
        this.storeName = storeName;
        this.rating = rating;
        this.price = productPrice;
        this.productImage = productImg;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
