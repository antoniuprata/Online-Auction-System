package com.demo.AuctionSystemBE.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@Entity
@Table(name = "picture", schema = "schemaproiecttw")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Picture {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="schemaproiecttw.picture_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idobject", referencedColumnName = "id")
    @JsonIgnore
    private Obj object;

    @Column(name = "image")
    private byte[] image;

    public Picture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Obj getObject() {
        return object;
    }

    public void setObject(Obj object) {
        this.object = object;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
