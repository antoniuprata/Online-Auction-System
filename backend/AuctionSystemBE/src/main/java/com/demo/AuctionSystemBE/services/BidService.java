package com.demo.AuctionSystemBE.services;

import com.demo.AuctionSystemBE.models.Bid;
import com.demo.AuctionSystemBE.models.User;
import com.demo.AuctionSystemBE.models.WatchList;
import com.demo.AuctionSystemBE.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BidService {
    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private WatchListService watchListService;

    @Autowired
    private ObjService objService;

    public String saveBid(final Bid bid){
        try {
            objService.checkIfActive(bid.getObject().getId());
        }catch (Exception e){
            return e.getMessage();
        }
        if (bid.getObject().getUser().equals(bid.getUser()))
            return "You can not bid for your product";
        Optional<Bid> lastBid = bidRepository.findLastBidForObjectId(bid.getObject().getId());
        if(lastBid.isPresent()){
            Bid lBid = lastBid.get();
            if (bid.getUser().equals(lBid.getUser()))
                return "You already bid for this product";
            if (bid.getPrice() <= lBid.getPrice())
                return "You need a bigger bid";
        }
        else{
            if (bid.getPrice() < bid.getObject().getInitialPrice())
                return "You need a bigger bid";
        }
        WatchList watchList = new WatchList();
        watchList.setUser(bid.getUser());
        watchList.setObject(bid.getObject());
        watchListService.saveWatchList(watchList);
        bidRepository.saveAndFlush(bid);
        return "Succes";
    }

    public List<Bid> findAllBids(){
        final List<Bid> bids = new ArrayList<>();
        bidRepository.findAll().forEach(bid -> bids.add(bid));
        return bids;
    }

    public List<Bid> findAllBidsByUserEmail(String email){
        final List<Bid> bids = new ArrayList<>();
        bidRepository.findAllBidsByEmail(email).forEach(bid -> bids.add(bid));
        return bids;
    }

    public Bid findLastBidForObjectId(Long idObject){
        Optional<Bid> bid = bidRepository.findLastBidForObjectId(idObject);
        if (bid.isPresent())
            return bid.get();
        else
            throw new RuntimeException("Could not find a bid for this object");
    }}
