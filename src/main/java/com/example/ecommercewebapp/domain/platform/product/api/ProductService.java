package com.example.ecommercewebapp.domain.platform.product.api;

import com.example.ecommercewebapp.domain.platform.product.impl.Product;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto, String shopAdminId);

    ProductDto getProduct(String productId);

    ProductDto update(ProductDto dto, String id);

    List<ProductDto> getAllProducts();

    void delete(String id);

    Product getProductEntity(String id);

    Product toEntity(ProductDto productDto);
    ProductDto toDto(Product product);
}
