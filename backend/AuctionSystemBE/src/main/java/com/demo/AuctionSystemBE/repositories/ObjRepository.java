package com.demo.AuctionSystemBE.repositories;

import com.demo.AuctionSystemBE.models.Obj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ObjRepository extends JpaRepository<Obj, Long> {

    @Transactional
    @Query("select o from Obj o join o.auction a where a.id = :id")
    List<Obj> findAllObjectsByAuction(Long id);
}
