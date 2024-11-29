package com.example.ecommerce;

public class Product {
    private String productId;
    private String title;
    private String description;
    private String imageUrl;
    private Long price;

    // Default constructor required for calls to DataSnapshot.getValue(Product.class)
    public Product() {
    }

    public Product(String productId, String title, String description, String imageUrl, Long price) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    // Getters and setters (optional, but recommended)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}

