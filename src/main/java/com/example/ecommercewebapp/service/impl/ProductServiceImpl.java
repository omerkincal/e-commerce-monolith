package com.example.ecommercewebapp.service.impl;

import com.example.ecommercewebapp.dto.ProductDto;
import com.example.ecommercewebapp.entity.Product;
import com.example.ecommercewebapp.entity.Shop;
import com.example.ecommercewebapp.repository.ProductRepository;
import com.example.ecommercewebapp.service.CategoryService;
import com.example.ecommercewebapp.service.ProductService;
import com.example.ecommercewebapp.service.ShopAdminService;
import com.example.ecommercewebapp.service.ShopService;
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
    public ProductDto save(ProductDto productDto, String shopAdminId) {
        if (Integer.parseInt(shopAdminId) == categoryService
                .getCategoryEntity(String.valueOf(productDto.getCategoryId()))
                .getShop().getShopAdmin().getShopAdminId()){
            Product product = toEntity(productDto);
            product = repository.save(product);
            return toDto(product);
        }
        return null;
    }

    @Override
    public ProductDto getProduct(String productId) {
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

    @Override
    public Product getProductEntity(String id){
        return repository.findById(Integer.parseInt(id)).get();
    }




    @Override
    public void delete(String id) {
        Product product = getProductEntity(id);
        repository.delete(product);
    }

    @Override
    public ProductDto toDto(Product product){
        return ProductDto.builder()
                .name(product.getName())
                .productId(product.getProductId())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getCategoryId())
                .build();
    }

    @Override
    public Product toEntity(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(categoryService.getCategoryEntity(String.valueOf(productDto.getCategoryId())));
        return product;
    }
}
