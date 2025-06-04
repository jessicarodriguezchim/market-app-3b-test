package com.tecdesoftware.market.persistance.crud;

import com.tecdesoftware.market.persistance.entity.Productos;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Productos, Integer> {

    //Query Methods: QUE HACE UNA CONSULTA
    //SQL: select *
    //FROM categoria
    //WHERE Id_categoria = ?
    //ORDER BY ASC
    List<Productos> findByCategoriaOderByNombreAcc(int idCategoria);

    Optional<List<Productos>> findByCantidadStockLessThanAndEstado (int cantidadStock, boolean estado);
}
