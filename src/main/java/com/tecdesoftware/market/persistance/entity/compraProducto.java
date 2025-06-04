package com.tecdesoftware.market.persistance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "compras_productos")
public class compraProducto {

    @EmbeddedId // sale de la otra clase
    private CompraProductoPK id;

    private Integer cantidad;
    private Double total;
    private Boolean estado;

    //Relación con la entidad Cliente : Muchas compras a un cliente
    @ManyToOne
    //No quiero que se modifique la entidad cliente, solo relacionarla
    @JoinColumn(name ="id_compra", insertable=false, updatable=false)
    private Compras compras;

    //Relación con la entidad Cliente : Muchas compras a un cliente
    @ManyToOne
    //No quiero que se modifique la entidad cliente, solo relacionarla
    @JoinColumn(name ="id_producto", insertable=false, updatable=false)
    private Productos productos;


    public CompraProductoPK getId() {
        return id;
    }

    public void setId(CompraProductoPK id) {
        this.id = id;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
