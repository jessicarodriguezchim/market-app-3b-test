package com.tecdesoftware.market.domain.repository;

import com.tecdesoftware.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    //Devolver todas las compras que se hagan de un cliente
    List<Purchase> getAll();
    //compras que haga un cliente en particular
    Optional <List<Purchase>> getByClient(String clienteId);
    Purchase save(Purchase purchase);
}
