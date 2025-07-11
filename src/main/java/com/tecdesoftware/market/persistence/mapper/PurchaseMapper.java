package com.tecdesoftware.market.persistence.mapper;

import com.tecdesoftware.market.domain.Purchase;
import com.tecdesoftware.market.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "Spring", uses = { PurcheseItemMapper.class } )
public interface PurchaseMapper {
    //Mapeo de las compras
    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clienteId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "productos", target = "items"),


    })
    //una compra
    Purchase toPurchase(Compra compra);
    //lista de compras
    List<Purchase> toPurchases(List<Compra> compras);
    @InheritInverseConfiguration
    @Mapping(target = "idCliente", ignore = true)
    Compra toCompra(Purchase purchase);

}
