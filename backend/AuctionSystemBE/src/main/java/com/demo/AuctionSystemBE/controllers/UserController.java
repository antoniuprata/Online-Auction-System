package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.models.User;
import com.demo.AuctionSystemBE.models.UserLogin;
import com.demo.AuctionSystemBE.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){return  userService.findAllUsers();}

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping(value = "/login")
    public User userLogin(@RequestBody UserLogin userLogin){
        return userService.userLogin(userLogin);
    }

    @PostMapping(value = "/new", consumes = {"application/json"})
    public User createUser(@RequestBody User newUser) throws Exception
    {
        if(newUser.getEmail().isEmpty() || newUser.getPassword().isEmpty() || newUser.getName().isEmpty())
            throw new Exception("Missing required fields");

        return userService.saveUser(newUser);
    }



}
