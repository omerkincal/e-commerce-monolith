package com.example.ecommercewebapp.domain.platform.product.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDto save(ProductDto productDto);

    ProductDto getById(String productId);

    ProductDto update(ProductDto dto, String id);

    Page<ProductDto> getAllProducts(Pageable pageable);

    void delete(String id);

}
