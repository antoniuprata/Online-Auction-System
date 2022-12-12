package com.demo.AuctionSystemBE.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user",  schema = "schemaproiecttw")
public class User {

    @Id
    @GeneratedValue(generator="user_id_seq")
    @SequenceGenerator(name="user_id_seq", sequenceName = "schemaproiecttw.user_id_seq")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "profilepicture")
    private String profilePicture;

    @OneToMany(mappedBy = "user")
    private List<WatchList> watchLists;

    @OneToMany(mappedBy = "user")
    private List<Bid> bids;

    @OneToMany(mappedBy = "user")
    private List<Auction> auctions;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<WatchList> getWatchLists() {
        return watchLists;
    }

    public void setWatchLists(List<WatchList> watchLists) {
        this.watchLists = watchLists;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }
}
