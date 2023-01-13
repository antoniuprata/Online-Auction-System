package com.demo.AuctionSystemBE.models.utils;

import com.demo.AuctionSystemBE.models.Obj;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WatchListReturn {
    private ArrayList<ProductsReturn> productsInWatchList;
}
