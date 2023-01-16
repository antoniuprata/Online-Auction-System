package com.demo.AuctionSystemBE.services;

import com.demo.AuctionSystemBE.models.Obj;
import com.demo.AuctionSystemBE.repositories.ObjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjService {
    @Autowired
    private ObjRepository objRepository;

    public Obj saveObj(final Obj obj) {
        return objRepository.saveAndFlush(obj);
    }

    public List<Obj> findAllObjs() {
        final List<Obj> objs = new ArrayList<>();
        objRepository.findAll().forEach(obj -> objs.add(obj));
        return objs;
    }

    public List<Obj> findAllActiveObjects() {
        final List<Obj> objs = new ArrayList<>();
        objRepository.findAllActiveObjects().forEach(obj -> objs.add(obj));
        return objs;
    }

    public List<Obj> findAllObjectsByUserEmail(String email) {
        final List<Obj> objects = new ArrayList<>();
        objRepository.findAllObjectsByEmail(email).forEach(object -> objects.add(object));
        return objects;
    }

    public Optional<Obj> findObjectById(Long id) {
        return objRepository.findObjectById(id);
    }

    public Obj checkIfActive(Long id) {
        Optional<Obj> product = objRepository.checkIfActive(id);
        ;
        if (product.isPresent()) {
            return product.get();
        } else
            throw new RuntimeException("Product not in auction");
    }

    public Obj updateObj(Long id, final Obj obj) {
        return objRepository.save(obj);
    }

    public void deleteObj(final Long id) {
        objRepository.deleteById(id);
    }

    public List<Obj> findAllInactiveObjects() {
        final List<Obj> objs = new ArrayList<>();
        objRepository.findAllInactiveObjects().forEach(obj -> objs.add(obj));
        return objs;
    }
}
