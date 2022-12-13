package com.demo.AuctionSystemBE.repositories;

import com.demo.AuctionSystemBE.models.Auction;
import com.demo.AuctionSystemBE.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Transactional
    @Query("select a from Auction a join a.user u  where u.email = :email")
    List<Auction> findAllAuctionsByEmail(String email);
}
