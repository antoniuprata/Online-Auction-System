package com.demo.AuctionSystemBE.models.utils;

import lombok.Data;

@Data
public class BidAdd {
    private String userEmail;
    private Long idProduct;
    private Double price;
}
