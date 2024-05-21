package com.cotizador.entity.mapper;

import com.cotizador.entity.dto.ProductDto;
import com.cotizador.entity.model.Product;

public class ProductMapper {
    public static ProductDto toDto(Product product) {
        return new ProductDto(product.getSKU(), product.getName(), product.getDescription(), product.getPrice());
    }
    public static Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setSKU(product.getSKU());
        product.setName(product.getName());
        product.setDescription(product.getDescription());
        product.setPrice(product.getPrice());
        return product;
    }
}
