package com.tecdesoftware.market.persistence;

import com.tecdesoftware.market.domain.Purchase;
import com.tecdesoftware.market.domain.repository.PurchaseRepository;
import com.tecdesoftware.market.persistence.crud.CompraCrudRepository;
import com.tecdesoftware.market.persistence.entity.Compra;
import com.tecdesoftware.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class CompraRepository implements PurchaseRepository {


    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clienteId) {
        return compraCrudRepository.findByidCliente(clienteId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override

    public Purchase save(Purchase purchase) {
        // traducir purchase a compras
        Compra compra = mapper.toCompra(purchase);
        //a todos los productos para cada uno se guarda en compra
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        //asegurarse que se guarden en cascada
        return mapper.toPurchase(compraCrudRepository.save(compra));

    }
}
