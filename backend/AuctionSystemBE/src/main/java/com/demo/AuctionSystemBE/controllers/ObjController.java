package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.services.ObjService;
import com.demo.AuctionSystemBE.models.Obj;
import com.demo.AuctionSystemBE.services.ObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/object")
public class ObjController {
    @Autowired
    private ObjService objService;

    @GetMapping
    public List<Obj> getObjs(){return  objService.findAllObjs();}

    @PostMapping(value="/new")
    public void create(@RequestBody final Obj obj){
        objService.saveObj(obj);
    }

    @PutMapping(value = "{id}")
    public void update(@PathVariable Long id, @RequestBody Obj obj){
        objService.updateObj(id,obj);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id){
        objService.deleteObj(id);
    }

}
