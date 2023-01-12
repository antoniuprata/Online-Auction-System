package com.demo.AuctionSystemBE.models.utils;

import java.sql.Timestamp;
import java.util.List;

public class ProductsAdd {
    private String title;
    private String description;
    private String category;
    private List<String> images;
    private Double startingPrice;
    private Timestamp endTime;
    private String userEmail;

    public ProductsAdd() {
    }

    public ProductsAdd(String title, String description, String category, List<String> images, Double startingPrice, Timestamp endTime, String userEmail) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.images = images;
        this.startingPrice = startingPrice;
        this.endTime = endTime;
        this.userEmail = userEmail;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
