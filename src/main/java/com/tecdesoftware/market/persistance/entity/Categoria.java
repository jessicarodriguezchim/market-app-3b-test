package com.tecdesoftware.market.persistance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column (name = "id_categoria")
    private int idCategoria;

    private String decripcion;
    private Boolean estado;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;

    }
    public String getDecripcion() {
        return decripcion;
    }
}
