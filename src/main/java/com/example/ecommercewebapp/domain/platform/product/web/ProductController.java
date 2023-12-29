package com.example.ecommercewebapp.domain.platform.product.web;

import com.example.ecommercewebapp.domain.platform.product.api.ProductService;
import com.example.ecommercewebapp.library.rest.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Log
public class ProductController extends BaseController {

    private final ProductService service;

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest productRequest){
        return toResponse(service.save(productRequest.toDto()));
    }

    @GetMapping("{id}")
    public ProductResponse getShopAdminById(@PathVariable String id){
        return toResponse(service.getById(id));
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




}
