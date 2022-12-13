package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.models.Auction;
import com.demo.AuctionSystemBE.models.User;
import com.demo.AuctionSystemBE.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping
    public List<Auction> getAuctions(){return  auctionService.findAllAuctions();}

    @GetMapping(value = "/byuser/{userEmail}")
    public List<Auction> getAuctionsByUserEmail(@PathVariable String userEmail){
        return auctionService.findAllAuctionsByUserEmail(userEmail);
    }

    @PostMapping(value="/new")
    public void create(@RequestBody final Auction auction){
        auctionService.saveAuction(auction);
    }

    @PutMapping(value = "{id}")
    public void update(@PathVariable Long id, @RequestBody Auction auction){
        auctionService.updateAuction(id,auction);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id){
        auctionService.deleteAuction(id);
    }


}
