package com.demo.AuctionSystemBE.repositories;

import com.demo.AuctionSystemBE.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    @Override
    Optional<User> findById(Long aLong);

    @Transactional
    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(String email);
}
