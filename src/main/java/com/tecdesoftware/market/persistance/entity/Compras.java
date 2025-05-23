package com.tecdesoftware.market.persistance.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "compras")
public class Compras {


    @Id //llave primaria
    //Hace el id autoincremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer idProducto;

    private String nombre;

    @Column(name ="id_categoria")
    private Integer idCategoria;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column (name = "cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;

