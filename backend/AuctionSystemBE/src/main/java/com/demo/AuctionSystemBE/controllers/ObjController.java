package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.models.Picture;
import com.demo.AuctionSystemBE.models.User;
import com.demo.AuctionSystemBE.models.utils.ProductsAdd;
import com.demo.AuctionSystemBE.services.ObjService;
import com.demo.AuctionSystemBE.models.Obj;
import com.demo.AuctionSystemBE.services.PictureService;
import com.demo.AuctionSystemBE.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ObjController {
    @Autowired
    private ObjService objService;
    @Autowired
    private UserService us;

    @Autowired
    private PictureService ps;

    @GetMapping("/all")
    public List<Obj> getObjs(){return  objService.findAllObjs();}

    @GetMapping
    public List<Obj> getActiveObjs(){return  objService.findAllObjs();}

    @PostMapping
    public void create(@RequestBody final ProductsAdd productsAdd){
        Obj obj = new Obj();
        obj.setTitle(productsAdd.getTitle());
        obj.setDescription(productsAdd.getDescription());
        obj.setEndDate(productsAdd.getEndTime());
        obj.setCategory(productsAdd.getCategory());
        obj.setInitialPrice(productsAdd.getStartingPrice());
        List<Picture> images = new ArrayList<>();
        User user = us.findByEmail(productsAdd.getUserEmail());
        obj.setUser(user);
        Obj createdObj = objService.saveObj(obj);
        for(String img : productsAdd.getImages()){
            Picture image = new Picture();
            image.setImage(img);
            image.setObject(createdObj);
            images.add(ps.savePicture(image));
        }
        createdObj.setPictures(images);
        objService.updateObj(createdObj.getId(), createdObj);
    }
    @PutMapping(value = "{id}")
    public void update(@PathVariable Long id, @RequestBody Obj obj){
        objService.updateObj(id,obj);
    }
}
