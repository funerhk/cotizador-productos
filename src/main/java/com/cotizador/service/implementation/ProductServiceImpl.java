package com.cotizador.service.implementation;

import com.cotizador.entity.dto.ProductDto;
import com.cotizador.entity.mapper.ProductMapper;
import com.cotizador.entity.model.Product;
import com.cotizador.entity.repository.ProductRepository;
import com.cotizador.exception.ProductNotFoundException;
import com.cotizador.service.interfaces.ProductService;
import com.cotizador.utils.ApiResponse;
import com.cotizador.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDto findById(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(Message.PRODUCT_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        return ProductMapper.toDto(product);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map((dto) -> new ProductDto(dto.getSKU(), dto.getName(), dto.getDescription(), dto.getPrice()))
                .toList();
    }

    @Override
    public ApiResponse delete(String productId) {
        ProductDto productDto = findById(productId);
        if (productDto == null) {
            throw new ProductNotFoundException(Message.PRODUCT_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now());
        }
        Product product = new Product();
        product.setSKU(productId);
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        productRepository.delete(product);
        return new ApiResponse(Message.PRODUCT_DELETE_SUCCESSFULLY, 204, HttpStatus.NO_CONTENT, LocalDateTime.now());
    }

    @Override
    public ApiResponse save(ProductDto dto) {
        Product product = new Product();
        product.setDescription(dto.description());
        product.setName(dto.name());
        product.setPrice(dto.price());
        productRepository.save(product);
        return new ApiResponse(Message.PRODUCT_SAVE_SUCCESSFULLY, 201, HttpStatus.CREATED, LocalDateTime.now());
    }

    @Override
    public ApiResponse update(String productId, ProductDto dto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(Message.PRODUCT_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        product.setDescription(dto.description());
        product.setName(dto.name());
        product.setPrice(dto.price());
        productRepository.save(product);
        return new ApiResponse(Message.PRODUCT_UPDATE_SUCCESSFULLY, 201, HttpStatus.OK, LocalDateTime.now());
    }

}
