package com.demo.AuctionSystemBE.repositories;

import com.demo.AuctionSystemBE.models.Obj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ObjRepository extends JpaRepository<Obj, Long> {

    @Transactional
    @Query("select o from Obj o join o.user u  where u.email = :email")
    List<Obj> findAllObjectsByEmail(String email);
}
