package com.example.ecommercewebapp.domain.platform.product.impl;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryService;
import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;
import com.example.ecommercewebapp.domain.platform.product.api.ProductService;
import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.exception.CoreException;
import com.example.ecommercewebapp.library.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;
    private CategoryService categoryService;

    public ProductServiceImpl(ProductRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }


    @Override
    public ProductDto save(ProductDto productDto) {
        return ProductMapper.toDto(repository.save(ProductMapper.toEntity(new Product(),productDto)),categoryService);
    }

    @Override
    public ProductDto getById(String productId) {
        return ProductMapper.toDto(repository.findById(productId).orElseThrow(()->
                new CoreException(MessageCodes.ENTITY_NOT_FOUND, Product.class.getSimpleName(), productId)),categoryService);
    }

    @Override
    public ProductDto update(ProductDto dto, String id) {
        Product product = repository.findById(id).orElseThrow(
                ()-> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Product.class.getSimpleName(), id));
        return ProductMapper.toDto(repository.save(setProduct(product, dto)),categoryService);
    }

    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return PageUtil.pageToDto(repository.findAll(pageable), product -> ProductMapper.toDto(product,categoryService));
    }

    @Override
    public void delete(String id) {
        var product = repository.findById(id).orElseThrow(
                ()-> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Product.class.getSimpleName(), id)
        );
        repository.delete(product);
    }

    private Product setProduct(Product product, ProductDto dto){
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategoryId(dto.getCategory().getId());
        product.setDescription(dto.getDescription());
        product.setStock(dto.getStock());
        return product;
    }
}
