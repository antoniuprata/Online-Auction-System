package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.models.Bid;
import com.demo.AuctionSystemBE.models.Picture;
import com.demo.AuctionSystemBE.models.User;
import com.demo.AuctionSystemBE.models.utils.ProductsAdd;
import com.demo.AuctionSystemBE.models.utils.ProductsReturn;
import com.demo.AuctionSystemBE.models.utils.UserLoginReturn;
import com.demo.AuctionSystemBE.services.BidService;
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

    @Autowired
    private BidService bs;

    @GetMapping("/all")
    public List<Obj> getObjs(){return  objService.findAllObjs();}

    @GetMapping
    public List<ProductsReturn> getActiveObjs(){
        List<ProductsReturn> products = new ArrayList<>();
        List<Obj> activeProducts = objService.findAllActiveObjects();
        for(Obj prod : activeProducts){
            ProductsReturn prodRet = new ProductsReturn();
            prodRet.setId(prod.getId());
            prodRet.setImages(prod.getPictures());
            prodRet.setEndTime(prod.getEndDate());
            prodRet.setDescription(prod.getDescription());
            prodRet.setCategory(prod.getCategory());
            prodRet.setTitle(prod.getTitle());
            prodRet.setStartingPrice(prod.getInitialPrice());
            Double currentPrice;
            try{
                Bid bid = bs.findLastBidForObjectId(prod.getId());
                currentPrice = bid.getPrice();
                System.out.println(prod.getId());
            }
            catch (Exception e){
                System.out.println("no");
                currentPrice = prod.getInitialPrice();
            }
            prodRet.setCurrentPrice(currentPrice);
            UserLoginReturn userRet = new UserLoginReturn();
            userRet.setPhone(prod.getUser().getPhone());
            userRet.setEmail(prod.getUser().getEmail());
            userRet.setName(prod.getUser().getName());
            prodRet.setUser(userRet);
            products.add(prodRet);
        }
        return products;
    }

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

    @GetMapping("/{idObject}")
    public Obj findById(@PathVariable Long idObject){
        return objService.findObjectById(idObject);
    }
}
