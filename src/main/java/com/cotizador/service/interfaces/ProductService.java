package com.cotizador.service.interfaces;

import com.cotizador.entity.dto.ProductDto;
import com.cotizador.utils.ApiResponse;

import java.util.List;

public interface ProductService {
    ProductDto findById(String productId);
    List<ProductDto> findAllProducts();
    ApiResponse delete(String productId);
    ApiResponse save(ProductDto dto);
    ApiResponse update(String productId, ProductDto dto);
}
