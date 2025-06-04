package com.tecdesoftware.market.persistance.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="clientes")
public class Cliente {

    @Id // indica que el PK
    //Ejemplo que no lleva : @GeneratedValue
    @Column (name = "id")
    private String id;

    private String nombre;
    private String apellido;
    private long celular;
    private String direccion;

    @Column(name = "correo_electronico")
    private String email;

    //Aqui se conecta con la entidad compra
    @OneToMany (mappedBy = "cliente")
    private List<Compras> compras;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public long getCelular() {
        return celular;
    }
    public void setCelular(long celular) {
        this.celular = celular;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
