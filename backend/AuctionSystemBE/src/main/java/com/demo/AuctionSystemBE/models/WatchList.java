package com.demo.AuctionSystemBE.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "watchlist", schema = "schemaproiecttw")
public class WatchList {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="schemaproiecttw.watchlist_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "idobject", referencedColumnName = "id")
    @JsonIgnore
    private Obj object;

    @Column(name = "date")
    private Timestamp date;

    public WatchList() {
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


}
