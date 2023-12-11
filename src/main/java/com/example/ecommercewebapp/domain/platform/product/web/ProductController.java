package com.example.ecommercewebapp.domain.platform.product.web;

import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;
import com.example.ecommercewebapp.domain.platform.product.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("{shopAdminId}")
    public ProductResponse save(@RequestBody ProductRequest productRequest,
                                @PathVariable String shopAdminId){
        return toResponse(service.save(productRequest.toDto(), shopAdminId));
    }

    @GetMapping("{id}")
    public ProductResponse getShopAdminById(@PathVariable String id){
        return toResponse(service.getProduct(id));
    }

    @PutMapping("{id}")
    public ProductResponse update(@PathVariable String id,
                               @RequestBody ProductRequest productRequest){
        return toResponse(service.update(productRequest.toDto(),id));
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable String id){
        service.delete(id);
        return "Başarıyla Silindi";
    }

    @GetMapping
    public List<ProductResponse> getAllShopAdmins(){
        return service.getAllProducts()
                .stream()
                .map(productDto -> toResponse(productDto))
                .collect(Collectors.toList());
    }

    public ProductResponse toResponse(ProductDto productDto){
        return ProductResponse.builder()
                .productId(productDto.getProductId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .categoryId(productDto.getCategoryId())
                .quantity(productDto.getQuantity())
                .build();
    }
}
