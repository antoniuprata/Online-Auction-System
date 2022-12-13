package com.demo.AuctionSystemBE.repositories;

import com.demo.AuctionSystemBE.models.Obj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjRepository extends JpaRepository<Obj, Long> {
}
