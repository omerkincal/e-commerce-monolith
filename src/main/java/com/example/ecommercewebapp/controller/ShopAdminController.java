package com.example.ecommercewebapp.controller;

import com.example.ecommercewebapp.dto.ProductDto;
import com.example.ecommercewebapp.dto.ShopAdminDto;
import com.example.ecommercewebapp.request.ShopAdminRequest;
import com.example.ecommercewebapp.response.ProductResponse;
import com.example.ecommercewebapp.response.ShopAdminResponse;
import com.example.ecommercewebapp.service.ShopAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("shop-admins")
@RequiredArgsConstructor
public class ShopAdminController {

    private final ShopAdminService service;

    @PostMapping
    public ShopAdminResponse save(@RequestBody ShopAdminRequest shopAdminRequest){
        return toResponse(service.save(shopAdminRequest.toDto()));
    }

    @GetMapping("{id}")
    public ShopAdminResponse getShopAdminById(@PathVariable String id){
        return toResponse(service.getShopAdmin(id));
    }

    @PutMapping("{id}")
    public ShopAdminResponse update(@PathVariable String id,
                                   @RequestBody ShopAdminRequest shopAdminRequest){
        return toResponse(service.update(shopAdminRequest.toDto(),id));
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable String id){
        service.delete(id);
        return "Başarıyla Silindi";
    }


    @GetMapping
    public List<ShopAdminResponse> getAllShopAdmins(){
        return service.getAllShopAdmin()
                .stream()
                .map(shopAdminDto -> toResponse(shopAdminDto))
                .collect(Collectors.toList());
    }

    public ShopAdminResponse toResponse(ShopAdminDto shopAdminDto){
        return ShopAdminResponse.builder()
                .id(shopAdminDto.getId())
                .name(shopAdminDto.getName())
                .surname(shopAdminDto.getSurname())
                .address(shopAdminDto.getAddress())
                .password(shopAdminDto.getPassword())
                .email(shopAdminDto.getEmail())
                .phone(shopAdminDto.getPhone())
                .message("Successfull")
                .code(200)
                .build();
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
