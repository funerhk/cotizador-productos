package com.cotizador.entity.repository;

import com.cotizador.entity.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findAllProductsByName(String name);
}
