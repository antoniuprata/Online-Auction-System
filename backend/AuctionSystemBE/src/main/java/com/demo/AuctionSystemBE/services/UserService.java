package com.demo.AuctionSystemBE.services;

import com.demo.AuctionSystemBE.models.User;
import com.demo.AuctionSystemBE.models.UserLogin;
import com.demo.AuctionSystemBE.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(final User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new RuntimeException("Email belongs to an account");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    public List<User> findAllUsers(){
        final List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }
    
    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        else
            throw new RuntimeException("Could not find a user with id: " + id);
    }

    public User findByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            return user.get();
        else
            throw new RuntimeException("Could not find a user with email: " + email);
    }

    public User userLogin(UserLogin userLogin){
        Optional<User> user = userRepository.findByEmail(userLogin.getEmail());
        if (user.isPresent()&& passwordEncoder.matches(userLogin.getPassword(), user.get().getPassword()))
            return user.get();
        else
            throw new RuntimeException("Could not find a user with this email and password");
    }

}
