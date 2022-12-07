package com.demo.AuctionSystemBE.services;

import com.demo.AuctionSystemBE.models.User;
import com.demo.AuctionSystemBE.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(final User user){
        return userRepository.saveAndFlush(user);
    }

    public List<User> findAllUsers(){
        final List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

}
