package com.example.ecommercewebapp.controller;


import com.example.ecommercewebapp.dto.BasketDto;
import com.example.ecommercewebapp.request.AddBasketRequest;
import com.example.ecommercewebapp.response.BasketResponse;
import com.example.ecommercewebapp.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("baskets")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService service;

    @PostMapping("add-product-to-basket")
    public BasketResponse addProductToBasket(@RequestBody AddBasketRequest addBasketRequest){
        return toResponse(service.addProductToBasket(addBasketRequest.toDto()));
    }

    @GetMapping("{customerId}")
    public BasketResponse getBasketById(@PathVariable String customerId){
        return toResponse(service.getBasketById(customerId));
    }

    @DeleteMapping("{basketItemId}")
    public String delete(@PathVariable String basketItemId){
        return service.removeProductFromBasket(basketItemId);
    }


    public BasketResponse toResponse(BasketDto basketDto){
        return BasketResponse.builder()
                .basketId(basketDto.getBasketId())
                .customerId(basketDto.getCustomer().getCustomerId())
                .totalAmount(basketDto.getTotalAmount())
                .basketItemList(basketDto.getBasketItemList())
                .build();
    }
}
