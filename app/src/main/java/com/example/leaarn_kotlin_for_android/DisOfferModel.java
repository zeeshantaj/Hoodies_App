package com.example.leaarn_kotlin_for_android;

public class DisOfferModel {
    private int image;
    private String offerPercentage;

    public DisOfferModel(int image, String offerPercentage) {
        this.image = image;
        this.offerPercentage = offerPercentage;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getOfferPercentage() {
        return offerPercentage;
    }

    public void setOfferPercentage(String offerPercentage) {
        this.offerPercentage = offerPercentage;
    }
}
