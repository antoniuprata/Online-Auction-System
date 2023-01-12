package com.demo.AuctionSystemBE.repositories;

import com.demo.AuctionSystemBE.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

}
