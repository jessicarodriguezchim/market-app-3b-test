package com.tecdesoftware.market.domain.service;

import com.tecdesoftware.market.domain.Product;
import com.tecdesoftware.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.boot.autoconfigure.container.ContainerImageMetadata.isPresent;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int id) {
        return productRepository.getProduct(id );
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save (Product product){
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        //Verificar que existe el producto que se desea eliminar
        if (getProduct(productId).isPresent()){
            productRepository.delete(productId);
            return true;
        } else {
            return false;
        }
    }


}
