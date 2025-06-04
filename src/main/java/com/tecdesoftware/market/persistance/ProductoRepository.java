package com.tecdesoftware.market.persistance;

import com.tecdesoftware.market.persistance.crud.ProductoCrudRepository;
import com.tecdesoftware.market.persistance.entity.Productos;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Le dice a Soring que se conecta a la base de datos
@Repository

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    //Me va a dar todos mis productos de mi BD
    public List<Productos> getAll() {
        //Convirtiendo un Iterable <T> a una lista de productos List<Producto>
        return (List<Productos>) productoCrudRepository.findAll();
    }

    //obtener los productos pro categoria
    public List<Productos> getByCategoria(int idCategoria) {
        return productoCrudRepository.findByCategoriaOderByNombreAcc(idCategoria);
    }
    //obtener productos que se vayan a agotar
    public Optional<List<Productos>> getByEscasos(int cantidad) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    //obtener un producto dado el id
    public Optional <Productos> getById(int id) {
        return productoCrudRepository.findById(id);
    }
    //guardar un producto - devuelve un producto con return, .save se encarga de guardar un producto.
    public Productos save(Productos producto) {
        return productoCrudRepository.save(producto);
    }
    //borrar un producto
    public void delete(Productos producto) {
        productoCrudRepository.delete(producto);
    }

}

