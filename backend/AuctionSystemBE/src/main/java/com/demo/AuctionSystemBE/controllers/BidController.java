package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.models.Bid;
import com.demo.AuctionSystemBE.models.Obj;
import com.demo.AuctionSystemBE.services.BidService;
import com.demo.AuctionSystemBE.services.ObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bid")
public class BidController {
    @Autowired
    private BidService bidService;

    @GetMapping(value = "/{userEmail}")
    public List<Bid> getBidsByUserEmail(@PathVariable String userEmail){
        return bidService.findAllBidsByUserEmail(userEmail);
    }

    @PostMapping()
    public void create(@RequestBody final Bid bid){
        bidService.saveBid(bid);
    }
}
