package com.demo.AuctionSystemBE.services;

import com.demo.AuctionSystemBE.models.Auction;
import com.demo.AuctionSystemBE.repositories.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    public Auction saveAuction(final Auction auction){
        return auctionRepository.saveAndFlush(auction);
    }

    public List<Auction> findAllAuctions(){
        final List<Auction> auctions = new ArrayList<>();
        auctionRepository.findAll().forEach(auction -> auctions.add(auction));
        return auctions;
    }

    public List<Auction> findAllAuctionsByUserEmail(String email){
        final List<Auction> auctions = new ArrayList<>();
        auctionRepository.findAllAuctionsByEmail(email).forEach(auction -> auctions.add(auction));
        return auctions;
    }

    public Auction updateAuction(Long id, final Auction auction){
        return auctionRepository.save(auction);
    }

    public void deleteAuction(final Long id){
        auctionRepository.deleteById(id);
    }
}
