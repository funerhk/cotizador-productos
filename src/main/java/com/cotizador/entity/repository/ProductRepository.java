package com.cotizador.entity.repository;

import com.cotizador.entity.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {
}
