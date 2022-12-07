package com.demo.AuctionSystemBE.repositories;

import com.demo.AuctionSystemBE.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
}
