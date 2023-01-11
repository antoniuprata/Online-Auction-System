package com.demo.AuctionSystemBE.services;

import com.demo.AuctionSystemBE.models.Bid;
import com.demo.AuctionSystemBE.models.User;
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

    public Bid saveBid(final Bid bid){
        return bidRepository.saveAndFlush(bid);
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
