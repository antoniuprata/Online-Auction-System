package com.demo.AuctionSystemBE.services;

import com.demo.AuctionSystemBE.models.Bid;
import com.demo.AuctionSystemBE.models.Picture;
import com.demo.AuctionSystemBE.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public Picture savePicture(final Picture picture){
        return pictureRepository.saveAndFlush(picture);
    }
}
