package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.models.Bid;
import com.demo.AuctionSystemBE.models.Obj;
import com.demo.AuctionSystemBE.models.User;
import com.demo.AuctionSystemBE.models.WatchList;
import com.demo.AuctionSystemBE.models.utils.ProductsReturn;
import com.demo.AuctionSystemBE.models.utils.UserLoginReturn;
import com.demo.AuctionSystemBE.models.utils.WatchListAdd;
import com.demo.AuctionSystemBE.models.utils.WatchListReturn;
import com.demo.AuctionSystemBE.services.BidService;
import com.demo.AuctionSystemBE.services.ObjService;
import com.demo.AuctionSystemBE.services.UserService;
import com.demo.AuctionSystemBE.services.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/watchlist")
public class WatchListController {

    @Autowired
    private WatchListService watchListService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjService objService;

    @Autowired
    private BidService bs;

    @GetMapping(value = "/{userEmail}")
    public List<ProductsReturn> getWatchListObjectsByUserEmail(@PathVariable String userEmail){
        List<WatchList> watchLists = watchListService.findAllWatchListObjectsByUserEmail(userEmail);
        List<ProductsReturn> products = new ArrayList<>();
        for(WatchList w: watchLists){
            ProductsReturn prodRet = new ProductsReturn();
            Obj prod = w.getObject();
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

    @PostMapping()
    public String create(@RequestBody final WatchListAdd watchListAdd){
        WatchList watchList = new WatchList();
        User user = userService.findByEmail(watchListAdd.getEmailUser());
        Obj product = objService.findObjectById(watchListAdd.getIdProduct());
        watchList.setObject(product);
        watchList.setUser(user);
        return watchListService.saveWatchList(watchList);
    }

    @DeleteMapping("/{userEmail}/{idProduct}")
    public String delete(@PathVariable String userEmail, @PathVariable Long idProduct){
       return watchListService.deleteWatchList(userEmail,idProduct);
    }
}
