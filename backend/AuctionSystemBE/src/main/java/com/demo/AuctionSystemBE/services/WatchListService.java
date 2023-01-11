package com.demo.AuctionSystemBE.services;

import com.demo.AuctionSystemBE.models.WatchList;
import com.demo.AuctionSystemBE.repositories.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WatchListService {
    @Autowired
    private WatchListRepository watchListRepository;

    public WatchList saveWatchList(final WatchList watchList){
        return watchListRepository.saveAndFlush(watchList);
    }

    public List<WatchList> findAllWatchListObjectsByUserEmail(String email){
        final List<WatchList> watchLists = new ArrayList<>();
        watchListRepository.findAllWatchListsObjectsByEmail(email).forEach(watchList -> watchLists.add(watchList));
        return watchLists;
    }
}
