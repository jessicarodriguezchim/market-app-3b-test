package com.tecdesoftware.market.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable //Esta clase es para crear la llave compuesta
public class CompraProductoPK {

    @Column(name = "id_compra")
    private int id;

    @Column(name ="id_prodcto")
    private int productoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
