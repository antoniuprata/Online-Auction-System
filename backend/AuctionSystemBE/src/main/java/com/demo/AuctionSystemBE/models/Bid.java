package com.demo.AuctionSystemBE.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "bid", schema = "schemaproiecttw")
public class Bid {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="schemaproiecttw.bid_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "idobject", referencedColumnName = "id")
    @JsonIgnore
    private Obj object;

    @Column(name = "price")
    private Double price;

    @Column(name = "date")
    private Timestamp date;

    public Bid() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Obj getObject() {
        return object;
    }

    public void setObject(Obj object) {
        this.object = object;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
