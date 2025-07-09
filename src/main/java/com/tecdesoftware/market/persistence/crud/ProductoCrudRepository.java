package com.tecdesoftware.market.persistence.crud;

import com.tecdesoftware.market.domain.Product;
import com.tecdesoftware.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //Query Method
    //Select *
    //FROM productos
    //Where id_categoria =5?
    //Order by nombre ASC
    List<Producto> findByIdCategoriaOrderByNombre(int idCategoria);

    //Cantidad en stock
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
