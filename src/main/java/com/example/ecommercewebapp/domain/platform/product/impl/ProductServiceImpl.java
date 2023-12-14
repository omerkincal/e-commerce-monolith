package com.example.ecommercewebapp.domain.platform.product.impl;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryService;
import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;
import com.example.ecommercewebapp.domain.platform.product.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final CategoryService categoryService;



    @Override
    public ProductDto save(ProductDto productDto) {
            Product product = toEntity(productDto);
            product = repository.save(product);
            return toDto(product);
    }

    @Override
    public ProductDto getById(String productId) {
        ProductDto productDto = toDto(repository.findById(Integer.parseInt(productId)).get());
        return productDto;
    }

    @Override
    public ProductDto update(ProductDto dto, String id) {
        Product product = getProductEntity(id);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.getCategoryEntity(String.valueOf(dto.getCategoryId())));
        product.setQuantity(dto.getQuantity());
        product = repository.save(product);
        return toDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(product -> toDto(product))
                .collect(Collectors.toList());
    }

    public Product getProductEntity(String id){
        return repository.findById(Integer.parseInt(id)).get();
    }




    @Override
    public void delete(String id) {
        Product product = getProductEntity(id);
        repository.delete(product);
    }

    public ProductDto toDto(Product product){
        return ProductDto.builder()
                .name(product.getName())
                .id(product.getProductId())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getCategoryId())
                .build();
    }

    public Product toEntity(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(categoryService.getCategoryEntity(String.valueOf(productDto.getCategoryId())));
        return product;
    }
}
