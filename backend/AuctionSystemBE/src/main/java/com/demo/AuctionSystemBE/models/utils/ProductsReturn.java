package com.demo.AuctionSystemBE.models.utils;

import com.demo.AuctionSystemBE.models.Picture;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ProductsReturn {
    private Long id;
    private String title;
    private String category;
    private List<Picture> images;
    private String description;
    private Double startingPrice;
    private Double currentPrice;
    private Timestamp endTime;
    private UserLoginReturn user;

}
