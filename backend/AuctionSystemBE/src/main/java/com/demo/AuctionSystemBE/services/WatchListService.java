package com.demo.AuctionSystemBE.services;

import com.demo.AuctionSystemBE.models.WatchList;
import com.demo.AuctionSystemBE.repositories.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WatchListService {
    @Autowired
    private WatchListRepository watchListRepository;

    public String saveWatchList(final WatchList watchList){
        Optional<WatchList> watchListOpt = watchListRepository.findWatchListObjectByEmailAndObjId(watchList.getUser().getEmail(), watchList.getObject().getId());
        if(watchListOpt.isPresent())
            return "Product already in watchlist";
        else{
            watchListRepository.saveAndFlush(watchList);
            return "Product added to watchlist";
        }
    }

    public List<WatchList> findAllWatchListObjectsByUserEmail(String email){
        final List<WatchList> watchLists = new ArrayList<>();
        watchListRepository.findAllWatchListsObjectsByEmail(email).forEach(watchList -> watchLists.add(watchList));
        return watchLists;
    }
}
