package com.example.ecommerce;

public class Card {
    private String imageUrl;
    private String title;
    private String description;
    private String longDescription;
    private double rating;
    private double price;

    public Card() {
    }


    public Card(String imageUrl, String title, String description, String longDescription, double rating, double price) {
        this.imageUrl = imageUrl;
        this.title= title;
        this.description = description;
        this.longDescription = longDescription;
        this.rating = rating;
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
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
}
