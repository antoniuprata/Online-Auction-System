package com.demo.AuctionSystemBE.services;

import com.demo.AuctionSystemBE.models.Auction;
import com.demo.AuctionSystemBE.models.Obj;
import com.demo.AuctionSystemBE.repositories.ObjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjService {
    @Autowired
    private ObjRepository objRepository;

    public Obj saveObj(final Obj obj){
        return objRepository.saveAndFlush(obj);
    }

    public List<Obj> findAllObjs(){
        final List<Obj> objs = new ArrayList<>();
        objRepository.findAll().forEach(obj -> objs.add(obj));
        return objs;
    }

    public List<Obj> findAllObjectsByAuction(Long id){
        final List<Obj> objs = new ArrayList<>();
        objRepository.findAllObjectsByAuction(id).forEach(obj -> objs.add(obj));
        return objs;
    }

    public Obj updateObj(Long id, final Obj obj){
        return objRepository.save(obj);
    }

    public void deleteObj(final Long id){
        objRepository.deleteById(id);
    }
}
