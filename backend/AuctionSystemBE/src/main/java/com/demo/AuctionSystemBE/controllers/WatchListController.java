package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.models.Obj;
import com.demo.AuctionSystemBE.models.User;
import com.demo.AuctionSystemBE.models.WatchList;
import com.demo.AuctionSystemBE.models.utils.WatchListAdd;
import com.demo.AuctionSystemBE.services.ObjService;
import com.demo.AuctionSystemBE.services.UserService;
import com.demo.AuctionSystemBE.services.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public class WatchListController {

    @Autowired
    private WatchListService watchListService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjService objService;

    @GetMapping(value = "/{userEmail}")
    public List<WatchList> getWatchListObjectsByUserEmail(@PathVariable String userEmail){
        return watchListService.findAllWatchListObjectsByUserEmail(userEmail);
    }

    @PostMapping()
    public void create(@RequestBody final WatchListAdd watchListAdd){
        WatchList watchList = new WatchList();
        User user = userService.findByEmail(watchListAdd.getEmailUser());
        Obj product = objService.findObjectById(watchListAdd.getIdProduct());
        watchList.setObject(product);
        watchList.setUser(user);
        watchListService.saveWatchList(watchList);
    }
}
