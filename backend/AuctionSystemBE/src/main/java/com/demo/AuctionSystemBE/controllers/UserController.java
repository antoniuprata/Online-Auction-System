package com.demo.AuctionSystemBE.controllers;

import com.demo.AuctionSystemBE.models.User;
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

    @PostMapping(value = "/new/{name}/{email}/{password}/{phone}")
    public User create(@PathVariable String name,@PathVariable String email,@PathVariable String password,@PathVariable String phone)
    {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        return userService.saveUser(user);
    }

}
