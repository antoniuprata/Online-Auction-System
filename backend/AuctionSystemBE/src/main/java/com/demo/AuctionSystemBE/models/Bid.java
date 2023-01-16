package com.demo.AuctionSystemBE.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "bid", schema = "schemaproiecttw")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(name = "date", insertable=false)
    private Timestamp date;

}
