package com.demo.AuctionSystemBE.repositories;

import com.demo.AuctionSystemBE.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Transactional
    @Query("select b from Bid b join b.user u where u.email = :email")
    List<Bid> findAllBidsByEmail(String email);

    @Transactional
    @Query(nativeQuery = true,value="SELECT *\n" +
            "\tFROM schemaproiecttw.bid where\n" +
            "\tidobject =:idObject order by price desc limit 1;")
    Optional<Bid> findLastBidForObjectId(Long idObject);
}
