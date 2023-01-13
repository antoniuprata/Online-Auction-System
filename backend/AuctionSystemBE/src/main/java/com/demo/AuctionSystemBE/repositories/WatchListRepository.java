package com.demo.AuctionSystemBE.repositories;

import com.demo.AuctionSystemBE.models.Obj;
import com.demo.AuctionSystemBE.models.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList, Long> {

    @Transactional
    @Query("select w from WatchList w join w.user u where u.email = :email")
    List<WatchList> findAllWatchListsObjectsByEmail(String email);

}
