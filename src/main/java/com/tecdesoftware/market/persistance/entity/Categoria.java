package com.tecdesoftware.market.persistance.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column (name = "id_categoria")
    private int idCategoria;

    private String decripcion;
    private Boolean estado;

    //Aqui se conecta con la entidad categoria
    @OneToMany (mappedBy = "categoria")
    private List<Productos> compras;

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
