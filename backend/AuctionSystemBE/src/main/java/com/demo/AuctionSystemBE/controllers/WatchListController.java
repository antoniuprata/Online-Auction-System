package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.models.WatchList;
import com.demo.AuctionSystemBE.services.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class WatchListController {

    @Autowired
    private WatchListService watchListService;

    @GetMapping(value = "/byUserEmail/{userEmail}")
    public List<WatchList> getWatchListObjectsByUserEmail(@PathVariable String userEmail){
        return watchListService.findAllWatchListObjectsByUserEmail(userEmail);
    }

    @PostMapping(value="/new")
    public void create(@RequestBody final WatchList watchList){
        watchListService.saveWatchList(watchList);
    }
}
