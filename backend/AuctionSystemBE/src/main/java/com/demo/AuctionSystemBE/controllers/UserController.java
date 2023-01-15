package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.models.User;
import com.demo.AuctionSystemBE.models.utils.UserLogin;
import com.demo.AuctionSystemBE.models.utils.UserLoginReturn;
import com.demo.AuctionSystemBE.models.utils.UserSignup;
import com.demo.AuctionSystemBE.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public UserLoginReturn userLogin(@RequestBody UserLogin userLogin){
        User userAllData = userService.userLogin(userLogin);
        UserLoginReturn user = new UserLoginReturn();
        user.setEmail(userAllData.getEmail());
        user.setName(userAllData.getName());
        user.setPhone(user.getPhone());
        return user;
    }

    @PostMapping(value = "/signup", consumes = {"application/json"})
    public String createUser(@RequestBody UserSignup newUser) throws Exception
    {
        if(newUser.getEmail().isEmpty() || newUser.getPassword().isEmpty() || newUser.getName().isEmpty())
            throw new Exception("Missing required fields");
        User user = new User();
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setPhone(newUser.getPhone());
        user.setName(newUser.getName());
        user.setAuctions(new ArrayList<>());
        user.setWatchLists(new ArrayList<>());
        user.setBids(new ArrayList<>());
        try {
            userService.saveUser(user);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Account created";
    }



}
