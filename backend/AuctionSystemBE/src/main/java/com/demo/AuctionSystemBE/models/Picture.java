package com.demo.AuctionSystemBE.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@Table(name = "picture", schema = "schemaproiecttw")
public class Picture {
    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idobject", referencedColumnName = "id")
    @JsonIgnore
    private Obj object;

    @Column(name = "path")
    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
