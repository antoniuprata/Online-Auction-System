package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.models.Bid;
import com.demo.AuctionSystemBE.models.Obj;
import com.demo.AuctionSystemBE.models.User;
import com.demo.AuctionSystemBE.models.utils.BidAdd;
import com.demo.AuctionSystemBE.services.BidService;
import com.demo.AuctionSystemBE.services.ObjService;
import com.demo.AuctionSystemBE.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bid")
public class BidController {
    @Autowired
    private BidService bidService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjService objService;


    @GetMapping(value = "/{userEmail}")
    public List<Bid> getBidsByUserEmail(@PathVariable String userEmail){
        return bidService.findAllBidsByUserEmail(userEmail);
    }

    @PostMapping()
    public String create(@RequestBody final BidAdd bidAdd){
        Bid bid = new Bid();
        User user = userService.findByEmail(bidAdd.getUserEmail());
        Obj product = objService.findObjectById(bidAdd.getIdProduct());
        bid.setObject(product);
        bid.setPrice(bidAdd.getPrice());
        bid.setUser(user);
        return bidService.saveBid(bid);
    }
}
