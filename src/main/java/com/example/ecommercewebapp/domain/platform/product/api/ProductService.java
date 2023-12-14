package com.example.ecommercewebapp.domain.platform.product.api;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);

    ProductDto getById(String productId);

    ProductDto update(ProductDto dto, String id);

    List<ProductDto> getAllProducts();

    void delete(String id);

}
