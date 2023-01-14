package com.demo.AuctionSystemBE.repositories;

import com.demo.AuctionSystemBE.models.Obj;
import com.demo.AuctionSystemBE.models.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList, Long> {

    @Transactional
    @Query("select w from WatchList w join w.user u where u.email = :email")
    List<WatchList> findAllWatchListsObjectsByEmail(String email);

    @Transactional
    @Query("select w from WatchList w " + "join w.user u join w.object o where u.email = :email and o.id = :idObject")
    Optional<WatchList> findWatchListObjectByEmailAndObjId(String email, Long idObject);

}
