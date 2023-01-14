package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.models.Bid;
import com.demo.AuctionSystemBE.models.Obj;
import com.demo.AuctionSystemBE.models.User;
import com.demo.AuctionSystemBE.models.WatchList;
import com.demo.AuctionSystemBE.models.utils.BidAdd;
import com.demo.AuctionSystemBE.services.BidService;
import com.demo.AuctionSystemBE.services.ObjService;
import com.demo.AuctionSystemBE.services.UserService;
import com.demo.AuctionSystemBE.services.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Obj> product = objService.findObjectById(bidAdd.getIdProduct());
        if (product.isPresent()){
            Obj prod = product.get();
            bid.setObject(prod);
            bid.setPrice(bidAdd.getPrice());
            bid.setUser(user);
            return bidService.saveBid(bid);
        }
        return null;
    }
}
